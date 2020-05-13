package com.baijia.lhy.controller;

import com.baijia.lhy.pojo.dto.Result;
import com.baijia.lhy.pojo.entity.ReceivePoint;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @PostMapping("/uploadOrder")
    public Result getReceivePoints(@RequestBody JSONObject jsonObject){


        System.out.println(jsonObject.toJSONString());
        Result result = new Result();
        result.setCode(2001);
        result.setMsg("");
        result.setData(jsonObject);
        return  result;
    }
}
