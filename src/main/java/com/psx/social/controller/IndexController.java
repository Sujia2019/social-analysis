package com.psx.social.controller;

import com.psx.social.entity.UserBase;
import com.psx.social.entity.UserDTO;
import com.psx.social.entity.VerifyCode;
import com.psx.social.service.SettingService;
import com.psx.social.service.UserService;
import com.psx.social.util.Constants;
import com.psx.social.util.ReturnT;
import com.psx.social.util.Verify;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api("登录首页")
@RestController
@RequestMapping("/")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    UserService userService;
    @Autowired
    SettingService settingService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    Verify verify;

    // 通用登录
    @ApiOperation("通用登录")
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> doLogin(@RequestBody(required = false) UserBase userBase, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ReturnT<?> returnT;
        UserDTO userDTO;
        if (session.getAttribute("user") != null) {
            // 说明已经登陆，session中有缓存，不必再次登录
            userDTO = (UserDTO) session.getAttribute("user");
            // 如果此次登录用户为上个用户
            if (userDTO.getUser_account().equals(userBase.getUser_account())) {
                // 将缓存对象直接返回
                LOGGER.info(">>>>>>登录用户：{}", userDTO);
                return new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG, userDTO);
            }
        }
        // 走业务层查库
        userDTO = userService.doLogin(userBase);
        if (userDTO != null) {
            returnT = new ReturnT<>(Constants.SUCCESS, Constants.SUCCESS_MSG, userDTO);
            session.setAttribute("user", userDTO);
        } else {
            returnT = new ReturnT<>(Constants.FAIL, "账号或密码错误，登录失败");
        }
        LOGGER.info(">>>>>>登录用户：{}", userDTO);
        return returnT;
    }

    @ApiOperation("发送验证码")
    @RequestMapping(value = "sendCode", method = RequestMethod.GET)
    @ResponseBody
    public ReturnT<?> sendCode(@RequestParam String phone) {
        // 发送验证码
        String code = verify.sendCode(phone);
        redisTemplate.boundValueOps(phone).set(code);
        return new ReturnT<>(Constants.SUCCESS, "发送成功");
    }

    @ApiOperation("验证验证码")
    @RequestMapping(value = "verify", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> verifyCode(@RequestBody VerifyCode verifyCode) {
        // 发送验证码
        String code = (String) redisTemplate.boundValueOps(verifyCode.getPhone()).get();
        if (Strings.isBlank(code)) {
            return new ReturnT<>(Constants.FAIL, "验证码已失效");
        } else if (verifyCode.getCode().equals(code)) {
            return new ReturnT<>(Constants.SUCCESS, "验证成功");

        } else {
            return new ReturnT<>(Constants.FAIL, "验证码错误");
        }
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "changePwd", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> verifyCode(@RequestBody UserBase userBase) {
        return userService.doChangePwd(userBase);
    }

    @ApiOperation("检查是否有此账号")
    @RequestMapping(value = "checkAccount", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> verifyAccount(@RequestBody String account) {
        if (userService.hasAccount(account)) {
            return new ReturnT<>(Constants.FAIL, "此账号已存在");
        }
        return new ReturnT<>(Constants.SUCCESS, "恭喜，此账号可以使用");
    }

    @ApiOperation("注销")
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public ReturnT<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        return new ReturnT<>(Constants.SUCCESS, "注销成功");
    }
}
