package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.dto.ShopCarGoods;
import com.baijia.lhy.pojo.entity.ShopCar;
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
 * @since 2020-04-24
 */
@Mapper
@Repository
public interface ShopCarMapper extends BaseMapper<ShopCar> {
     List<ShopCarGoods> getShopCarList(int userId);
}
