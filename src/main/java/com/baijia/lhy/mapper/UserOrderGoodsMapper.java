package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.dto.UserOrderAndGoodsList;
import com.baijia.lhy.pojo.entity.UserOrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lhy
 * @since 2020-05-15
 */
@Mapper
@Repository
public interface UserOrderGoodsMapper extends BaseMapper<UserOrderGoods> {
    List<UserOrderAndGoodsList> getOrderUnpayList(int userId);
}
