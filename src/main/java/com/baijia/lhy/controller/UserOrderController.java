package com.baijia.lhy.controller;


import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.pojo.entity.UserOrder;
import com.baijia.lhy.pojo.entity.UserOrderGoods;
import com.baijia.lhy.service.IShopCarService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    IUserService userService;

    @Autowired
    IShopCarService shopCarService;

    @Autowired
    IUserOrderService userOrderService;

    @Autowired
    IUserOrderGoodsService userOrderGoodsService;

    @PostMapping("/uploadOrder")
    public Result uploadOrder(@RequestBody String str) {//这里可以用net.minidev.json.JSONObject直接入参，但不能用org.json.JSONObject
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //根据入参token查找用户信息
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", jsonObject.getString("token"));

            System.out.println("&&&&&&token:" + jsonObject.getString("token"));
            User user = userService.getOne(queryWrapper);
            if (user == null || user.getUserId() <= 0) {
                result.setCode(3006);
                result.setMsg("用户token错误");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }

            UserOrder userOrder = new UserOrder();
            ////////////////////////////////////////
            userOrder.setUserId(user.getUserId());

            JSONObject receivePoint = jsonObject.getJSONObject("receivePoint");
            userOrder.setReceivePointId(receivePoint.getInt("id"));

            userOrder.setReceiverName(jsonObject.getString("receiverUserName"));
            userOrder.setReceiverPhone(jsonObject.getString("receiverUserPhone"));

            userOrder.setEstimateTotalCost(Double.valueOf(jsonObject.getString("totalPrice")));

            userOrder.setPayType("cash");
            userOrder.setStatus(0);//0待支付

            LocalDateTime time = LocalDateTime.now();
            userOrder.setCreateTime(time);
            userOrder.setUpdateTime(time);

            time = time.plusDays(1);//收货时间顺延一天
            userOrder.setPlanReceiveTime(time.toLocalDate());

            boolean success = userOrderService.save(userOrder);
            JSONArray productsArray = jsonObject.getJSONArray("productsArray");

            if(success){//添加到订单商品关联表中
                //插入数据到订单商品关联表中
                List userOrderGoodsList = new ArrayList<UserOrderGoods>();
                for (int i = 0; i < productsArray.length(); i++) {
                    JSONObject jsonObject1 = productsArray.getJSONObject(i);
                    JSONObject goods = jsonObject1.getJSONObject("goods");
                    UserOrderGoods userOrderGoods = new UserOrderGoods();
                    userOrderGoods.setCount(jsonObject1.getInt("shopCarCount"));
                    userOrderGoods.setGoodsId(jsonObject1.getInt("goodsId"));
                    userOrderGoods.setImg(goods.getString("img"));
                    userOrderGoods.setOldPrice(goods.getDouble("oldPrice"));
                    userOrderGoods.setPrice(goods.getDouble("price"));
                    userOrderGoods.setTitle(goods.getString("title"));
                    userOrderGoods.setOrderId(userOrder.getOrderId());

                    userOrderGoodsList.add(userOrderGoods);
                }
                userOrderGoodsService.saveBatch(userOrderGoodsList);
            }

            if (success) {//更新用户的收货地址信息
                user.setReceivePointId(receivePoint.getInt("id"));
                user.setReceiverName(jsonObject.getString("receiverUserName"));
                user.setReceiverPhone(jsonObject.getString("receiverUserPhone"));
                userService.saveOrUpdate(user);
                //删除订单库中的数据
                List list = new ArrayList<>();
                for (int i = 0; i < productsArray.length(); i++) {
                    list.add(productsArray.getJSONObject(i).getInt("goodsId"));
                }
                queryWrapper = new QueryWrapper();
                queryWrapper.eq("user_id", user.getUserId());
                queryWrapper.in("goods_id", list);
                shopCarService.remove(queryWrapper);
                //
                result.setCode(2001);
                result.setMsg("");
                result.setData(new net.minidev.json.JSONObject());//这里用org.json.JSONObject会报错，不知道为啥？序列化傻的报错
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //失败
        result.setCode(3006);
        result.setMsg("处理失败");
        result.setData(new net.minidev.json.JSONObject());
        return result;
    }

    /**
     * 获取待支付订单列表
     *
     * @param str
     * @return
     */
    @PostMapping("/getOrderUnpayList")
    public Result getOrderUnpayList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //根据入参token查找用户信息
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", jsonObject.getString("token"));

            System.out.println("&&&&&&sessionKey:" + jsonObject.getString("token"));
            User user = userService.getOne(queryWrapper);
            if (user == null || user.getUserId() <= 0) {
                result.setCode(3006);
                result.setMsg("用户token错误");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }

            List list = userOrderGoodsService.getOrderUnpayList(user.getUserId());

            //
            result.setCode(2001);
            result.setMsg("");
            result.setData(list);
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

    /**
     * 获取待收货订单列表
     *
     * @param str
     * @return
     */
    @PostMapping("/getOrderWaitReceiveList")
    public Result getOrderWaitReceiveList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //根据入参token查找用户信息
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", jsonObject.getString("token"));

            System.out.println("&&&&&&sessionKey:" + jsonObject.getString("token"));
            User user = userService.getOne(queryWrapper);
            if (user == null || user.getUserId() <= 0) {
                result.setCode(3006);
                result.setMsg("用户token错误");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }

            List list = userOrderGoodsService.getOrderWaitReceiveList(user.getUserId());

            //
            result.setCode(2001);
            result.setMsg("");
            result.setData(list);
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

    /**
     * 获取已完成订单列表
     *
     * @param str
     * @return
     */
    @PostMapping("/getOrderFinishedList")
    public Result getOrderFinishedList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //根据入参token查找用户信息
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", jsonObject.getString("token"));

            System.out.println("&&&&&&sessionKey:" + jsonObject.getString("token"));
            User user = userService.getOne(queryWrapper);
            if (user == null || user.getUserId() <= 0) {
                result.setCode(3006);
                result.setMsg("用户token错误");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }

            List list = userOrderGoodsService.getOrderFinishedList(user.getUserId());

            //
            result.setCode(2001);
            result.setMsg("");
            result.setData(list);
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

    /**
     * 获取已取消订单列表
     *
     * @param str
     * @return
     */
    @PostMapping("/getOrderCancledList")
    public Result getOrderCancledList(@RequestBody String str) {
        Result result = new Result();
        try {
            JSONObject jsonObject = new JSONObject(str);
            //根据入参token查找用户信息
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("token", jsonObject.getString("token"));

            System.out.println("&&&&&&sessionKey:" + jsonObject.getString("token"));
            User user = userService.getOne(queryWrapper);
            if (user == null || user.getUserId() <= 0) {
                result.setCode(3006);
                result.setMsg("用户token错误");
                result.setData(new net.minidev.json.JSONObject());
                return result;
            }

            List list = userOrderGoodsService.getOrderCancledList(user.getUserId());

            //
            result.setCode(2001);
            result.setMsg("");
            result.setData(list);
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

