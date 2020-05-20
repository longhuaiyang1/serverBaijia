package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.pojo.entity.UserOrder;
import com.baijia.lhy.service.IUserOrderGoodsService;
import com.baijia.lhy.service.IUserOrderService;
import com.baijia.lhy.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QrCodeReceiveListController {

    @Autowired
    IUserService userService;

    @Autowired
    IUserOrderService userOrderService;

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
                    List list = getReceiveListInPoint(receiverUser.getUserId(), LocalDate.now());

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


    @PostMapping("/finishQrCodeReceiveList")
    public Result finishQrCodeReceiveList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            String token = jsonObject.getString("token");
            String receiverToken = jsonObject.getString("receiverToken");//取货人员的token
            JSONArray orderIdsJsonArray = jsonObject.getJSONArray("orderIdsList");
            List list = new ArrayList();
            for (int i = 0; i < orderIdsJsonArray.length(); i++) {
                list.add(orderIdsJsonArray.get(i));
            }

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
                    boolean suc = finishReceiveListInPoint(receiverUser.getUserId(),list);
                    if(suc){
                        result.setCode(2001);
                        result.setMsg("");
                        result.setData(null);
                        return result;
                    }
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
     *
     * @return
     */
    public List getReceiveListInPoint(int userId, LocalDate planReceiveTime) {
        try {
            List list = userOrderGoodsService.getReceiveListInPoint(userId, planReceiveTime);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList();
    }


    /**
     * 更新该当天收货的订单为结束状态
     *
     * @param userId
     * @return
     */
    public boolean finishReceiveListInPoint(int userId, List<Integer> orderIds) {
        try {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("user_id", userId);
            queryWrapper.in("order_id",orderIds);
            UserOrder userOrder = new UserOrder();
            userOrder.setStatus(2);
            return userOrderService.update(userOrder,queryWrapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
