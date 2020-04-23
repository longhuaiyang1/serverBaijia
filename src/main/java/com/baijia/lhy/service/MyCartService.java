package com.baijia.lhy.service;

import com.baijia.lhy.pojo.entity.ShopCar;
import net.minidev.json.JSONObject;

public interface MyCartService {
    //添加购物车商品
    boolean addGoodsToMyCart(JSONObject jsonObject);

    //删除购物车商品
    boolean deleteByGoodsIdAndUserId(JSONObject jsonObject);
}
