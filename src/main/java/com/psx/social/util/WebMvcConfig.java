package com.psx.social.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    // 需要走拦截器的接口
    private static List<String> list;

    static {
        // 通过静态代码块开启项目只执行一次,
        list = new ArrayList<String>();
        list.add("/board/**"); // 需要拦截的接口
        list.add("/stu/**");
        list.add("/settings/**");
        list.add("/question/**");
//        list.add("/friend/**");
        list.add("/admin/**");
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // new ConfigInterceptor()是第一步定义的拦截器
        registry.addInterceptor(new ConfigInterceptor()).addPathPatterns(list);
    }


}
