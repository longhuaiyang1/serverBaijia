package com.baijia.lhy.service;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.dto.ShopCarGoods;
import com.baijia.lhy.pojo.entity.ShopCar;
import com.baomidou.mybatisplus.extension.service.IService;
import net.minidev.json.JSONObject;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
public interface IShopCarService extends IService<ShopCar> {

    Result addGoodsToMyCart(JSONObject jsonObject);

    Result deleteByGoodsIdAndUserId(JSONObject jsonObject);

    List<ShopCarGoods> getShopCarList(JSONObject jsonObject);
}
