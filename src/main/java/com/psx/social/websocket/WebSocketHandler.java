package com.psx.social.websocket;

import com.psx.social.dao.ChatRoomMapper;
import com.psx.social.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
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

    private static ChatRoomMapper chatRoomMapper;

    //此处是解决无法注入的关键
    private static ApplicationContext applicationContext;
    //要注入的service或者dao
    private QuestionService questionService;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketHandler.applicationContext = applicationContext;
    }

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
        questionService = applicationContext.getBean(QuestionService.class);
        // 处理发送的消息
        questionService.doAnalyze(message);
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