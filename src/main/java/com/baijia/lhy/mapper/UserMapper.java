package com.baijia.lhy.mapper;

import com.baijia.lhy.pojo.dto.UserReceivePoint;
import com.baijia.lhy.pojo.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
      UserReceivePoint getUserReceiveInfo(String token);
}
