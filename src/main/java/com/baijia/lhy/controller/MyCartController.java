package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.entity.ShopCar;
import com.baijia.lhy.service.MyCartService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/myCart")
public class MyCartController {

    @Autowired
    MyCartService myCartService;

    @PostMapping("/add")
    public boolean addGoodsToMyCart(@RequestBody JSONObject jsonObject){//添加商品到购物车
       return myCartService.addGoodsToMyCart(jsonObject);
    }

    @PostMapping("/delete")
    public boolean deleteGoodsFromMyCart(@RequestBody JSONObject jsonObject){//从我的购物车删除商品
        return myCartService.deleteByGoodsIdAndUserId(jsonObject);
    }
//
//    @GetMapping("/getAllGoods")
//    public List<MyCartGoods>  getMyCartGoods(){//获取我的购物车商品列表
//
//    }
}
