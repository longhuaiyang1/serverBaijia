package com.baijia.lhy.config;

import com.baijia.lhy.mapper.UserMapper;
import com.baijia.lhy.pojo.entity.User;
import com.baijia.lhy.service.IUserService;
import com.baijia.lhy.utils.MD5Util;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.StringUtils;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//        //拿到当前登录的对象
//        Subject subject = SecurityUtils.getSubject();
//        //获取到User
//        User currentUser = (User) subject.getPrincipal();
//        //设置当前用户的权限
//        if(!StringUtils.isEmpty(currentUser.getWxSessionKey())){
//            info.addStringPermission("authc");
//            info.addStringPermission(currentUser.getPerms());
//        }
//        return info;
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //目前小程序传过来的用户名，我这里是小程序登录，所以我这里的用户名是微信用户的oppenId，
        String openid = token.getUsername();
        String session_key = String.valueOf(token.getPassword());

        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(session_key)) {
            return null;//认证失败,返回空，shiro会抛出异常 UnknownAccountException
        }

        QueryWrapper wrapper = new QueryWrapper<>();
        wrapper.eq("wx_open_id", openid);
        User user = userService.getOne(wrapper);
        if (user == null) {
            user = new User();
        }
        user.setWxOpenId(openid);
        user.setWxSessionKey(session_key);
        user.setToken(MD5Util.md5Encrypt32Lower(session_key));
        boolean success = userService.saveOrUpdate(user);

        if (success) {
            //密码可以加密  MD5加密   MD5盐值加密
            //密码认证，shiro做,这样做是为了防止泄露密码
            return new SimpleAuthenticationInfo(user, user.getWxSessionKey(), "");
        } else {
            return null;//认证失败,返回空，shiro会抛出异常 UnknownAccountException
        }

    }
}
