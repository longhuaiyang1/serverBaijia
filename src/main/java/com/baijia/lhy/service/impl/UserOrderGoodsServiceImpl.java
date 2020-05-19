package com.baijia.lhy.service.impl;

import com.baijia.lhy.pojo.dto.UserOrderAndGoodsList;
import com.baijia.lhy.pojo.entity.UserOrderGoods;
import com.baijia.lhy.mapper.UserOrderGoodsMapper;
import com.baijia.lhy.service.IUserOrderGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2020-05-15
 */
@Service
public class UserOrderGoodsServiceImpl extends ServiceImpl<UserOrderGoodsMapper, UserOrderGoods> implements IUserOrderGoodsService {

    @Override
    public List<UserOrderAndGoodsList> getOrderUnpayList(int userId) {
        UserOrderGoodsMapper userOrderGoodsMapper = getBaseMapper();
        return userOrderGoodsMapper.getOrderUnpayList(userId);
    }

    @Override
    public List<UserOrderAndGoodsList> getOrderWaitReceiveList(int userId) {
        UserOrderGoodsMapper userOrderGoodsMapper = getBaseMapper();
        return userOrderGoodsMapper.getOrderWaitReceiveList(userId);
    }

    @Override
    public List<UserOrderAndGoodsList> getOrderFinishedList(int userId) {
        UserOrderGoodsMapper userOrderGoodsMapper = getBaseMapper();
        return userOrderGoodsMapper.getOrderFinishedList(userId);
    }

    @Override
    public List<UserOrderAndGoodsList> getOrderCancledList(int userId) {
        UserOrderGoodsMapper userOrderGoodsMapper = getBaseMapper();
        return userOrderGoodsMapper.getOrderCancledList(userId);
    }

    @Override
    public List<UserOrderAndGoodsList> getReceiveListInPoint(int userId) {
        UserOrderGoodsMapper userOrderGoodsMapper = getBaseMapper();
        return userOrderGoodsMapper.getReceiveListInPoint(userId);
    }
}
