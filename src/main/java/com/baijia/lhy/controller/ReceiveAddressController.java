package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.dto.UserReceivePoint;
import com.baijia.lhy.pojo.entity.ReceivePoint;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.IUserService;
import com.baijia.lhy.service.impl.ReceivePointServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReceiveAddressController {

    @Autowired
    IUserService userService;

    @PostMapping("/uploadReceiveAddress")
    public Result uploadReceiveAddress(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            String token = jsonObject.getString("token");
            int receivePoint = jsonObject.getJSONObject("receivePoint").getInt("id");
            String receiverUserName = jsonObject.getString("receiverUserName");
            String receiverUserPhone = jsonObject.getString("receiverUserPhone");

            User user = new User();
            user.setReceivePointId(receivePoint);
            user.setReceiverName(receiverUserName);
            user.setReceiverPhone(receiverUserPhone);

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", token);

            boolean suc = userService.saveOrUpdate(user, queryWrapper);
            if (suc) {
                result.setCode(2001);
                result.setMsg("");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(3006);
        result.setMsg("失败");
        result.setData(new net.minidev.json.JSONObject());
        return result;
    }
}
