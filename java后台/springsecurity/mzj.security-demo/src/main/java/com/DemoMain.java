package com;

import com.web.filter.TimeFilter;
import com.web.filter.TimeInterceptor;
import org.assertj.core.api.UrlAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoMain {

    public static void main(String[] args) {
        SpringApplication.run(DemoMain.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello spring security!";
    }

//    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter());
        List<String> urls = new ArrayList<String>();
        urls.add("/*");
        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

    @Bean
    public TimeInterceptor getTimeInterceptor(){
        return new TimeInterceptor();
    }
}
