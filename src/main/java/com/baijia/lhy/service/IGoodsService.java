package com.baijia.lhy.service;

import com.baijia.lhy.pojo.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
public interface IGoodsService extends IService<Goods> {
     List<Goods> getAllGoods();
}
