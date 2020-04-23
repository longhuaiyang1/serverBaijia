package com.baijia.lhy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "desc", defaultValue = "World") String desc) {
        return String.format("Hello baijia %s!", desc);
    }
}
