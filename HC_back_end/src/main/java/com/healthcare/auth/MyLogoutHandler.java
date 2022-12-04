package com.healthcare.auth;

import cn.hutool.core.util.StrUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 退出Handler
 */
@Component("myLogoutHandler")
public class MyLogoutHandler extends JSONAuthentication implements LogoutHandler {


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String headerToken = request.getHeader("Authorization");
        System.out.println("logout header Token = " + headerToken);
        System.out.println("logout request getMethod = " + request.getMethod());
        //
        if (!StrUtil.isEmpty(headerToken)) {
            //postMan测试时，自动假如的前缀，要去掉。
            String token = headerToken.replace("Bearer", "").trim();
            System.out.println("authentication = " + authentication);
            SecurityContextHolder.clearContext();
        }
    }
}