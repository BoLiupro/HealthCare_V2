
package com.healthcare.auth;

import com.healthcare.response.Result;
import com.healthcare.response.ResultCode;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myInvalidSessionStrategy")
public class MyInvalidSessionStrategy extends JSONAuthentication implements InvalidSessionStrategy {
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Result result = Result.error(ResultCode.USER_SESSION_INVALID);
        this.WriteJSON(request,response,result);
    }
}