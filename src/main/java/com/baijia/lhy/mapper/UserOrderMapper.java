package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.entity.UserOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(UserOrder record);

    int insertSelective(UserOrder record);

    UserOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(UserOrder record);

    int updateByPrimaryKey(UserOrder record);
}