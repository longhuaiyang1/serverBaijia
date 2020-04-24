package com.baijia.lhy.controller;

import com.baijia.lhy.service.impl.ShopCarServiceImpl;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myCart")
public class MyCartController {

    @Autowired
    ShopCarServiceImpl shopCarService;

    @PostMapping("/add")
    public boolean addGoodsToMyCart(@RequestBody JSONObject jsonObject){//添加商品到购物车
       return shopCarService.addGoodsToMyCart(jsonObject);
    }

    @PostMapping("/delete")
    public boolean deleteGoodsFromMyCart(@RequestBody JSONObject jsonObject){//从我的购物车删除商品
        return shopCarService.deleteByGoodsIdAndUserId(jsonObject);
    }
//
//    @GetMapping("/getAllGoods")
//    public List<MyCartGoods>  getMyCartGoods(){//获取我的购物车商品列表
//
//    }
}
