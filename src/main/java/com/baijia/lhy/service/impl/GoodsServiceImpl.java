package com.baijia.lhy.service.impl;

import com.baijia.lhy.pojo.entity.Goods;
import com.baijia.lhy.mapper.GoodsMapper;
import com.baijia.lhy.service.IGoodsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<Goods> getAllGoods() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ne("goods_id","0");
        return goodsMapper.selectList(queryWrapper);
    }
}
