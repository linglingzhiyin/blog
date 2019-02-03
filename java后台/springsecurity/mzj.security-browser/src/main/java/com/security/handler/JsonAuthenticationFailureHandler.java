package com.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JsonAuthenticationFailureHandler  implements AuthenticationFailureHandler {

    private boolean allowSessionCreation = true;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        saveException(request, exception);

        writeJsonToResponse(response);
    }

    protected void writeJsonToResponse(HttpServletResponse response) throws IOException {
        String json = "{\"code\":-1,\"desc\":\"失败\"}";
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(json);
    }

    protected final void saveException(HttpServletRequest request,
                                       AuthenticationException exception) {
        HttpSession session = request.getSession(false);

        if (session != null || allowSessionCreation) {
            request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
                    exception);
        }
    }
}
