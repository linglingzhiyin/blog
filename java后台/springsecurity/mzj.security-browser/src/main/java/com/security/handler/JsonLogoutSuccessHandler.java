package com.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        writeJsonToResponse(response);
    }


    protected void writeJsonToResponse(HttpServletResponse response) throws IOException {
        String json = "{\"code\":0,\"desc\":\"成功\"}";
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }
}