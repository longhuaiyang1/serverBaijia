package com.baijia.lhy.controller;

import com.baijia.lhy.service.IUserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public String login(@RequestBody JSONObject jsonObject) {
        String code = jsonObject.getAsString("code");
        return userService.wx_login(code).toJSONString();
    }

}
