package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.dto.ShopCarGoods;
import com.baijia.lhy.pojo.entity.ShopCar;
import com.baijia.lhy.service.IShopCarService;
import com.baijia.lhy.service.impl.ShopCarServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myCart")
public class MyCartController {

    @Autowired
    IShopCarService shopCarService;

    @PostMapping("/add")
    public Result addGoodsToMyCart(@RequestBody JSONObject jsonObject){//添加商品到购物车
       return shopCarService.addGoodsToMyCart(jsonObject);
    }

    @PostMapping("/delete")
    public Result deleteGoodsFromMyCart(@RequestBody JSONObject jsonObject){//从我的购物车删除商品
        return shopCarService.deleteByGoodsIdAndUserId(jsonObject);
    }

    @PostMapping("/getShopCarList")
    public List<ShopCarGoods> getShopCarList(@RequestBody JSONObject jsonObject){
        return shopCarService.getShopCarList(jsonObject);
    }
}
