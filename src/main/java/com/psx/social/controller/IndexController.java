package com.psx.social.controller;

import com.psx.social.entity.UserBase;
import com.psx.social.entity.UserDTO;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/")
public class IndexController {
    @Autowired
    UserService userService;
    @Autowired
    SettingService settingService;

    // 通用登录
    @RequestMapping(value = "doLogin",method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> doLogin(@RequestBody(required = false) UserBase userBase, HttpServletRequest request){
        HttpSession session = request.getSession();
        ReturnT<?> returnT;
        UserDTO userDTO;
        if(session.getAttribute("user")!=null){
            // 说明已经登陆，session中有缓存，不必再次登录
            userDTO= (UserDTO)session.getAttribute("user");
            // 将缓存对象直接返回
            returnT = new ReturnT<>(Constants.SUCCESS,Constants.SUCCESS_MSG,userDTO);
        }else{
            // 走业务层查库
            userDTO = userService.doLogin(userBase);
            if (userDTO != null){
                returnT = new ReturnT<>(Constants.SUCCESS,Constants.SUCCESS_MSG,userDTO);
                session.setAttribute("user",returnT);
            }else{
                returnT = new ReturnT<>(Constants.FAIL,"用户名或密码错误，登录失败");
            }
        }
        return returnT;
    }
}
