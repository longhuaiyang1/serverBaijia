package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.IUserOrderGoodsService;
import com.baijia.lhy.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QrCodeReceiveListController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserOrderGoodsService userOrderGoodsService;

    @PostMapping("/getQrCodeReceiveList")
    public Result getQrCodeReceiveList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            String token = jsonObject.getString("token");
            String receiverToken = jsonObject.getString("receiverToken");//取货人员的token

            //判断扫码人是提货点管理员
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", token);
            User user = userService.getOne(queryWrapper);
            if (user != null && user.getManagePointId() > 0) {
                queryWrapper = new QueryWrapper();
                queryWrapper.eq("token", receiverToken);
                User receiverUser = userService.getOne(queryWrapper);
                //判断管理员提货点与收货人提货点是同一个
                if (receiverUser != null && receiverUser.getReceivePointId() == user.getManagePointId()) {
                    List list = getReceiveListInPoint(receiverUser.getUserId());

                    result.setCode(2001);
                    result.setMsg("");
                    result.setData(list);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setCode(3006);
        result.setMsg("失败");
        result.setData(new net.minidev.json.JSONObject());
        return result;
    }


    /**
     * 获取取货点领取的货物列表
     * @return
     */
    public List getReceiveListInPoint(int userId) {
        try {
            List list = userOrderGoodsService.getReceiveListInPoint(userId);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }
}
