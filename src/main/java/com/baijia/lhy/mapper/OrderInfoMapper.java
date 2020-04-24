package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

}
