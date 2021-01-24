package com.psx.social.controller;
import com.psx.social.entity.UserBase;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    UserService userService;

    // 学生注册
    @RequestMapping(value = "doRegistry",method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> doRegistry(@RequestBody UserBase userBase, HttpServletRequest request) {
        return userService.doRegistry(userBase);
    }

}
