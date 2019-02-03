package com.web.filter;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class TimeInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("preHandle");
        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        Long start=(Long)request.getAttribute("startTime");
        System.out.println("postHandle耗时："+(new Date().getTime()-start));
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
        Long start=(Long)request.getAttribute("startTime");
        System.out.println("afterCompletion耗时："+(new Date().getTime()-start));
        System.out.println(ex);
    }
}
