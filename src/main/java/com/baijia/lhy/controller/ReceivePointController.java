package com.baijia.lhy.controller;


import com.baijia.lhy.pojo.entity.ReceivePoint;
import com.baijia.lhy.service.impl.ReceivePointServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lhy
 * @since 2020-05-12
 */
@RestController
public class ReceivePointController {
    @Autowired
    ReceivePointServiceImpl receivePointService;

    @GetMapping("/getReceivePoints")
    public List<ReceivePoint> getReceivePoints(){
        return receivePointService.list();
    }
}

