package com.baijia.lhy.controller;

import com.baijia.lhy.service.IUserService;
import com.baijia.lhy.utils.MD5Util;
import net.minidev.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
public class LoginController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public String login(@RequestBody JSONObject jsonObject) {
        String code = jsonObject.getAsString("code");
        //服务器端调用微信接口，根据code获取用户的
        jsonObject = userService.wx_login(code);
        String openid = jsonObject.getAsString("openid");
        String session_key = jsonObject.getAsString("session_key");
        if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(session_key)){
            return "微信登录失败";
        }

        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(openid, session_key);
        try {
            subject.login(token); //执行登录认证方法，如果没有异常说明OK
            jsonObject.put("token", MD5Util.md5Encrypt32Lower(session_key));
            return jsonObject.toJSONString();
        } catch (UnknownAccountException e) {   //用户名不存在
            return "用户名不存在";
        } catch (IncorrectCredentialsException e) {   //密码错误
            return "密码错误";
        }
    }

}
