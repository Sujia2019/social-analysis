package com.psx.social.util;


import com.psx.social.entity.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class ConfigInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 从session中获取用户信息
        UserDTO comUser = (UserDTO) session.getAttribute("user");

        // session过期
        if (comUser == null) {
            log.info(">>>>>>>session过期, 跳至登录页");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            // 通过接口跳转登录页面, 注:重定向后下边的代码还会执行 ; /outToLogin是跳至登录页的后台接口
            // response.sendRedirect("/login.html");
            return false;
        } else {
            return true;
        }
    }
}
