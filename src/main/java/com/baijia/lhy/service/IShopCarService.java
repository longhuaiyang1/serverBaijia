package com.baijia.lhy.service;

import com.baijia.lhy.pojo.entity.ShopCar;
import com.baomidou.mybatisplus.extension.service.IService;
import net.minidev.json.JSONObject;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
public interface IShopCarService extends IService<ShopCar> {

    boolean addGoodsToMyCart(JSONObject jsonObject);

    boolean deleteByGoodsIdAndUserId(JSONObject jsonObject);
}
