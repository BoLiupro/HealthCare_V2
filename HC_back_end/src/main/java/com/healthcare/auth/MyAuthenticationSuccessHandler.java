package com.healthcare.auth;


import com.healthcare.mapper.TUserMapper;
import com.healthcare.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功操作
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler {

    @Autowired
    TUserMapper tUserMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Result result = Result.ok().message("登录成功");
        result.data("id",tUserMapper.getOneUser(authentication.getName()).getId());
        result.data("photo",tUserMapper.getOneUser(authentication.getName()).getPhoto());
        result.data("name",authentication.getName());
        result.data("auth",authentication.getAuthorities());
        /*//处理编码方式，防止中文乱码的情况
        response.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        //这是springboot中jackson给我们提供的一种能将类或者其他对象转成json的方式
        response.getWriter().write(new ObjectMapper().writeValueAsString(result));*/
        //输出
        this.WriteJSON(request, response, result);
    }
}