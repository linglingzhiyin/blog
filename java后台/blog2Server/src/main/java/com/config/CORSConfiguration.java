package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
@Slf4j
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //允许访问哪个接口，允许站外的http://localhost:8989访问,allowedOrigins这里天的地址是将来要请求你的地址，例如baidu要请求你，就写百度
                registry.addMapping("/**")
                        //设置允许跨域请求的域名
                        .allowedOrigins("*")
                        //是否允许证书 不再默认开启
                        .allowCredentials(true)
                        //设置允许的方法
                        .allowedMethods("*")
                        //跨域允许时间
                        .maxAge(3600);
            }
        };
    }

}
