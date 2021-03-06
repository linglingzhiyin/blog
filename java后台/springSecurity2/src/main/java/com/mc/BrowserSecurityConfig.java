package com.mc;

import com.decision.CompositeAccessDecisionManager;
import com.decision.RoleAccessDecisionVoter;
import com.decision.SupportAccessDecisionVoter;
import com.decision.UriAccessDecisionVoter;
import com.handler.JsonAccessDeniedHandler;
import com.handler.JsonAuthenticationFailureHandler;
import com.handler.JsonAuthenticationSuccessHandler;
import com.handler.JsonLogoutSuccessHandler;
import com.metadatasource.DatabaseFilterInvocationSecurityMetadataSource;
import com.user.password.FakePasswordEncoder;
import com.user.service.FakeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import java.util.Arrays;

@EnableWebSecurity
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService() {
        return new FakeUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new FakePasswordEncoder();
    }

    @Bean(initMethod = "load")
    public DatabaseFilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        return new DatabaseFilterInvocationSecurityMetadataSource();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        return new CompositeAccessDecisionManager(
                Arrays.asList(new SupportAccessDecisionVoter(), new RoleAccessDecisionVoter(),
                        new UriAccessDecisionVoter()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O fsi){
                                fsi.setAccessDecisionManager(accessDecisionManager());
                                fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
                                return fsi;
                            }
                        }
                )
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/example/loginProcess")
                .successHandler(new JsonAuthenticationSuccessHandler())
                .failureHandler(new JsonAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/example/logoutProcess")
                .logoutSuccessHandler(new JsonLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new JsonAccessDeniedHandler());
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true)
                .ignoring()
                .antMatchers("/images/**", "/js/**", "/css/**");
    }
}
