package com.baijia.lhy.service;

import com.baijia.lhy.pojo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import net.minidev.json.JSONObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lhy
 * @since 2020-04-24
 */
public interface IUserService extends IService<User> {
    JSONObject wx_login(String code);
}
