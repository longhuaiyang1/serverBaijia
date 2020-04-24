package com.baijia.lhy.service.impl;

import com.baijia.lhy.mapper.UserMapper;
import com.baijia.lhy.pojo.entity.ShopCar;
import com.baijia.lhy.mapper.ShopCarMapper;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.IShopCarService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
@Service
public class ShopCarServiceImpl extends ServiceImpl<ShopCarMapper, ShopCar> implements IShopCarService {
    @Autowired
    ShopCarMapper shopCarMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean addGoodsToMyCart(JSONObject jsonObject) {
        int count = 1;//接口默认添加一份订单
        String session_key = jsonObject.getAsString("session_key");
        Integer goods_id = (Integer) jsonObject.get("goods_id");


        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("wx_session_key",session_key);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        }

        if (StringUtils.isEmpty(session_key) || goods_id <= 0) {
            return false;
        } else {
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getUserId());
            wrapper.eq("goods_id",goods_id);
            ShopCar shopCar = shopCarMapper.selectOne(wrapper);
            //商品订购数量加1
            if (shopCar == null) {
                shopCar = new ShopCar();
                shopCar.setCount(1);
                shopCar.setUserId(user.getUserId());
                shopCar.setGoodsId(goods_id);
                shopCarMapper.insert(shopCar);
            } else {
                shopCar.setCount(shopCar.getCount() + 1);
                shopCarMapper.updateById(shopCar);
            }
            return true;
        }
    }

    @Override
    public boolean deleteByGoodsIdAndUserId(JSONObject jsonObject) {
        int count = 1;//接口默认删除一份订单
        String session_key = jsonObject.getAsString("session_key");
        Integer goods_id = (Integer) jsonObject.get("goods_id");

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("wx_session_key",session_key);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return false;
        }

        if (StringUtils.isEmpty(session_key) || goods_id <= 0) {
            return false;
        } else {
            wrapper = new QueryWrapper<>();
            wrapper.eq("user_id",user.getUserId());
            wrapper.eq("goods_id",goods_id);
            ShopCar shopCar = shopCarMapper.selectOne(wrapper);
            shopCar.setCount(shopCar.getCount() - 1);
            if (shopCar.getCount() <= 0) {
                shopCarMapper.deleteById(shopCar.getShopCarId());
                return true;
            } else {
                shopCarMapper.updateById(shopCar);
                return true;
            }
        }

    }
}
