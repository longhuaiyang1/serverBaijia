package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.entity.ShopCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShopCarMapper {
    ShopCar selectByGoodsIdAndUserId(Integer userId, Integer goodsId);

    boolean deleteByGoodsIdAndUserId(ShopCar record);

    int deleteByPrimaryKey(Integer shopCarId);

    int insert(ShopCar record);

    int insertSelective(ShopCar record);

    ShopCar selectByPrimaryKey(Integer shopCarId);

    int updateByPrimaryKeySelective(ShopCar record);

    int updateByPrimaryKey(ShopCar record);
}