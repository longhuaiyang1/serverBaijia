package com.baijia.lhy.service.impl;

import com.baijia.lhy.pojo.entity.Goods;
import com.baijia.lhy.pojo.entity.ReceivePoint;
import com.baijia.lhy.mapper.ReceivePointMapper;
import com.baijia.lhy.service.IReceivePointService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lhy
 * @since 2020-05-12
 */
@Service
public class ReceivePointServiceImpl extends ServiceImpl<ReceivePointMapper, ReceivePoint> implements IReceivePointService {
}
