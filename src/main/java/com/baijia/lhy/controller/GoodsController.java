package com.baijia.lhy.controller;

import com.baijia.lhy.mapper.GoodsMapper;
import com.baijia.lhy.pojo.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    @GetMapping("/getAllGoods")
    public List<Goods> getAllGoods(){//获取商品列表
        return goodsMapper.getAllGoods();
    }
}
