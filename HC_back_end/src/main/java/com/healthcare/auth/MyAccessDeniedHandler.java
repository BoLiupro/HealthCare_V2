package com.healthcare.auth;

import com.healthcare.response.Result;
import com.healthcare.response.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足处理器
 */
@Component("myAccessDeniedHandler")
public class MyAccessDeniedHandler extends JSONAuthentication implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        Result result = Result.error(ResultCode.NO_PERMISSION)/*.message(e.getMessage())*/;

        this.WriteJSON(request,response,result);
    }
}