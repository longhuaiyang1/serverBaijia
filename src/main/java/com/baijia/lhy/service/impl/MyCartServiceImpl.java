package com.baijia.lhy.service.impl;

import com.baijia.lhy.mapper.ShopCarMapper;
import com.baijia.lhy.mapper.UserMapper;
import com.baijia.lhy.pojo.entity.ShopCar;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.MyCartService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MyCartServiceImpl implements MyCartService {
    @Autowired
    ShopCarMapper shopCarMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addGoodsToMyCart(JSONObject jsonObject) {
        int count = 1;//接口默认添加一份订单
        String session_key = jsonObject.getAsString("session_key");
        Integer goods_id = (Integer) jsonObject.get("goods_id");

        User user = userMapper.selectByWxSessionKey(session_key);
        if (user == null) {
            return false;
        }

        if (StringUtils.isEmpty(session_key) || goods_id <= 0) {
            return false;
        } else {
            ShopCar shopCar = shopCarMapper.selectByGoodsIdAndUserId(user.getUserId(), goods_id);
            //商品订购数量加1
            if (shopCar == null) {
                shopCar = new ShopCar();
                shopCar.setCount(1);
                shopCar.setUserId(user.getUserId());
                shopCar.setGoodsId(goods_id);
                shopCarMapper.insertSelective(shopCar);
            } else {
                shopCar.setCount(shopCar.getCount() + 1);
                shopCarMapper.updateByPrimaryKeySelective(shopCar);
            }
            return true;
        }
    }

    @Override
    public boolean deleteByGoodsIdAndUserId(JSONObject jsonObject) {
        int count = 1;//接口默认删除一份订单
        String session_key = jsonObject.getAsString("session_key");
        Integer goods_id = (Integer) jsonObject.get("goods_id");

        User user = userMapper.selectByWxSessionKey(session_key);
        if (user == null) {
            return false;
        }

        if (StringUtils.isEmpty(session_key) || goods_id <= 0) {
            return false;
        } else {
            ShopCar shopCar = shopCarMapper.selectByGoodsIdAndUserId(user.getUserId(), goods_id);
            shopCar.setCount(shopCar.getCount() - 1);
            if (shopCar.getCount() <= 0) {
                shopCarMapper.deleteByPrimaryKey(shopCar.getShopCarId());
                return true;
            } else {
                shopCarMapper.updateByPrimaryKeySelective(shopCar);
                return true;
            }
        }

    }
}
