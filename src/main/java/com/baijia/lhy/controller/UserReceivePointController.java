package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.dto.UserReceivePoint;
import com.baijia.lhy.service.IUserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserReceivePointController {
    @Autowired
    IUserService userService;

    @PostMapping("/getUserReceiveInfo")
    public Result getUserReceiveInfo(@RequestBody String str) {//这里可以用net.minidev.json.JSONObject直接入参，但不能用org.json.JSONObject
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            UserReceivePoint userReceivePoint = userService.getUserReceiveInfo(jsonObject.getString("token"));

            result.setCode(2001);
            result.setMsg("");
            result.setData(userReceivePoint);//这里用org.json.JSONObject会报错，不知道为啥？序列化傻的报错
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        //失败
        result.setCode(3006);
        result.setMsg("处理失败");
        result.setData(new net.minidev.json.JSONObject());
        return result;
    }
}
