package com.baijia.lhy.controller;

import com.baijia.lhy.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {


    @Autowired
    UserService userService;

//    @PostMapping("/login")
//    public String login(@RequestBody Login login) {
//       String code = login.getCode();
//       return userService.wx_login(code).toJSONString();
//    }

    @PostMapping("/login")
    public String login(@RequestBody JSONObject jsonObject) {
        String code = jsonObject.getAsString("code");
        return userService.wx_login(code).toJSONString();
    }

}
