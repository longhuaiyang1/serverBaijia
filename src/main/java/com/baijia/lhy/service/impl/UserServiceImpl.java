
package com.baijia.lhy.service.impl;

import com.baijia.lhy.mapper.UserMapper;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.UserService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    String appId = "wx7d5e459d34ee4251";
    String appSecret = "585edb778309637f1db078889ce4b214";

    @Autowired
    UserMapper userMapper;

    @Override
    public JSONObject wx_login(String code) {
        //添加下面这一段，才能让数据被解析，否则会报错
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(messageConverters); //添加下面这一段，才能让数据被解析，否则会报错

        JSONObject jsonObject = restTemplate.getForEntity("https://api.weixin.qq.com/sns/jscode2session?appid="+appId
                        +"&secret="+appSecret
                        +"&js_code="+ code
                        +"&grant_type=authorization_code"
                , JSONObject.class).getBody();

        String openid = jsonObject.getAsString("openid");
        String session_key = jsonObject.getAsString("session_key");
        //openid 微信用户唯一标识；  session_key 会话密钥；
        System.out.println("openid: "+openid+"&session_key:"+session_key);


        User user = userMapper.selectByWxOpenId(openid);
        if(user!=null) {//数据库存在该用户，更新数据
            user.setWxSessionKey(session_key);
            userMapper.updateByPrimaryKeySelective(user);

            //返回数据添加用户头像和
        }else{//数据库不存在该微信用户数据，插入该数据
            user = new User();
            user.setWxOpenId(openid);
            user.setWxSessionKey(session_key);
            userMapper.insertSelective(user);
        }

        return jsonObject;
    }
}
