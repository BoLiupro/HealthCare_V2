package com.healthcare.auth;

import com.healthcare.response.Result;
import com.healthcare.response.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  匿名用户访问无权限资源时的异常
 */
@Component("myAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint extends JSONAuthentication implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_NOT_LOGIN);
        this.WriteJSON(request,response,result);
    }
}