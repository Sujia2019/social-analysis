package com.psx.social.websocket;

import com.psx.social.entity.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebsocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public SpringWebSocketHandler infoHandler() {
        return new SpringWebSocketHandler();
    }

//    @RequestMapping("/websocket/login")
//    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String username = request.getParameter("username");
//        System.out.println(username+"登录");
//        System.out.println();
//        HttpSession session = request.getSession();
//        session.setAttribute("SESSION_USERNAME", username);
//        response.sendRedirect("test/chat.html");
//        return "test/chat";
//    }

    @RequestMapping("/websocket/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String account = ((UserInfo)request.getSession().getAttribute("user")).getUser_account();
        System.out.println("sendFrom:"+account);
        infoHandler().sendMessageToUsers(new TextMessage("你好，测试！！！！"));
        return null;
    }
}
