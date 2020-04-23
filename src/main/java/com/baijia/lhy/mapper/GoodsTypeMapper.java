package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.entity.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsTypeMapper {
    int deleteByPrimaryKey(Integer goodsTypeId);

    int insert(GoodsType record);

    int insertSelective(GoodsType record);

    GoodsType selectByPrimaryKey(Integer goodsTypeId);

    int updateByPrimaryKeySelective(GoodsType record);

    int updateByPrimaryKey(GoodsType record);
}