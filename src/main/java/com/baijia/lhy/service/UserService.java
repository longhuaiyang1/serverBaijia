package com.baijia.lhy.service;

import net.minidev.json.JSONObject;

public interface UserService {
    JSONObject wx_login(String code);
}
