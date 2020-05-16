package com.baijia.lhy.service;

import com.baijia.lhy.pojo.dto.UserOrderAndGoodsList;
import com.baijia.lhy.pojo.entity.UserOrderGoods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2020-05-15
 */
public interface IUserOrderGoodsService extends IService<UserOrderGoods> {

    List<UserOrderAndGoodsList>  getOrderUnpayList(int userId);

    List<UserOrderAndGoodsList>  getOrderWaitReceiveList(int userId);

    List<UserOrderAndGoodsList>  getOrderFinishedList(int userId);

    List<UserOrderAndGoodsList> getOrderCancledList(int userId);
}
