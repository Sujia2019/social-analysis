package com.psx.social.websocket;

import com.aliyuncs.exceptions.ClientException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.psx.social.dao.ChatRoomMapper;
import com.psx.social.entity.ChatRoom;
import com.psx.social.util.Constants;
import com.psx.social.util.NLPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("appLogAppender");
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketHandler> webSocketSet = new CopyOnWriteArraySet<WebSocketHandler>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @Autowired
    ChatRoomMapper chatRoomMapper;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        LOGGER.info("有新连接加入！当前在线人数为:{}", getOnlineCount());

        //群发消息
        for (WebSocketHandler item : webSocketSet) {
            try {
                item.sendMessage("有新连接加入！当前在线人数为" + getOnlineCount());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        LOGGER.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        LOGGER.info("来自客户端的消息:" + message);
        // 处理发送的消息
        doAnalyze(message);
        // 群发消息
        for (WebSocketHandler item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("发生错误");
        error.printStackTrace();
    }

    private void doAnalyze(String message) {
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(message).getAsJsonObject();
        JsonObject userAccount = jsonObject.get("userName").getAsJsonObject();
        JsonObject msg = jsonObject.get("msg").getAsJsonObject();
        ChatRoom chatRoom = chatRoomMapper.getChatInfo(userAccount.toString());
        if (chatRoom == null) {
            chatRoom = new ChatRoom();
            chatRoom.setActivity_index(100);
            chatRoom.setMsg_count(1);
            chatRoom.setUser_account(userAccount.toString());
            chatRoomMapper.insertChatRoom(chatRoom);
        } else {
            long msgCount = chatRoom.getMsg_count() + 1L;
            chatRoom.setMsg_count(msgCount);
            long last = chatRoom.getCreate_time().getTime();
            long now = System.currentTimeMillis();
            // 计算活跃度 条数/时间
            long activity_index = msgCount * Constants.DAY / (now - last);
            chatRoom.setActivity_index(activity_index);
            // 调用情感分析API
            try {
                String data = NLPUtil.getData(msg.toString());
                // 解析JSON
                JsonObject dataJson = parser.parse(data).getAsJsonObject();
                JsonObject positive_prob = dataJson.get("positive_prob").getAsJsonObject();
                JsonObject negative_prob = dataJson.get("negative_prob").getAsJsonObject();
                JsonObject neutral_prob = dataJson.get("neutral_prob").getAsJsonObject();
                JsonObject sentiment = dataJson.get("sentiment").getAsJsonObject();
                if (sentiment.toString().equals("负面")) {
                    long negative = chatRoom.getNegative() + 1L;
                    chatRoom.setNegative(negative);
                } else {
                    long positive = chatRoom.getPositive() + 1L;
                    chatRoom.setPositive(positive);
                }
                double positive_probD = Double.parseDouble(positive_prob.toString());
                double negative_probD = Double.parseDouble(negative_prob.toString());
                double neutral_probD = Double.parseDouble(neutral_prob.toString());

                double last_positive = chatRoom.getPositive_prob();
                double last_negative = chatRoom.getNegative();
                double last_neutral = chatRoom.getNeutral_prob();
                positive_probD = (positive_probD + last_positive) / msgCount;
                negative_probD = (negative_probD + last_negative) / msgCount;
                neutral_probD = (neutral_probD + last_neutral) / msgCount;

                chatRoom.setPositive_prob(positive_probD);
                chatRoom.setNegative_prob(negative_probD);
                chatRoom.setNeutral_prob(neutral_probD);

                // 更新
                chatRoomMapper.update(chatRoom);
            } catch (ClientException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketHandler item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketHandler.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketHandler.onlineCount--;
    }
}