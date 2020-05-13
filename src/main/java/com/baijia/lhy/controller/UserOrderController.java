package com.baijia.lhy.controller;


import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.entity.UserOrder;
import com.baijia.lhy.service.IUserOrderService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lhy
 * @since 2020-05-13
 */
@RestController
public class UserOrderController {
    @Autowired
    IUserOrderService userOrderService;

    @PostMapping("/uploadOrder")
    public Result getReceivePoints(@RequestBody String str) {//这里可以用net.minidev.json.JSONObject直接入参，但不能用org.json.JSONObject
        try {
            JSONObject jsonObject = new JSONObject(str);
            UserOrder userOrder = new UserOrder();
            ////////////////////////////////////////
            userOrder.setUserId(1);

            JSONObject receivePoint = jsonObject.getJSONObject("receivePoint");
            userOrder.setReceivePointId(receivePoint.getInt("id"));

            userOrder.setReceiverName(jsonObject.getString("receiverUserName"));
            userOrder.setReceiverPhone(jsonObject.getString("receiverUserPhone"));
            userOrder.setProducts(jsonObject.getJSONArray("productsArray").toString());
            userOrder.setEstimateTotalCost(Double.valueOf(jsonObject.getString("totalPrice")));

            userOrder.setPayType("cash");
            userOrder.setStatus(0);//0待支付

            LocalDateTime time = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Shanghai")));
            userOrder.setCreateTime(time);
            userOrder.setUpdateTime(time);

            boolean success = userOrderService.save(userOrder);

            Result result = new Result();
            if (success) {
                result.setCode(2001);
                result.setMsg("");
                result.setData(new net.minidev.json.JSONObject());//这里用org.json.JSONObject会报错，不知道为啥？序列化傻的报错
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //失败
        Result result = new Result();
        result.setCode(3006);
        result.setMsg("失败");
        result.setData(new net.minidev.json.JSONObject());
        return result;
    }
}

