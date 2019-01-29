package com.authority;

import com.authority.decision.CompositeAccessDecisionManager;
import com.authority.decision.RoleAccessDecisionVoter;
import com.authority.decision.SupportAccessDecisionVoter;
import com.authority.handler.JsonAccessDeniedHandler;
import com.authority.handler.JsonAuthenticationFailureHandler;
import com.authority.handler.JsonAuthenticationSuccessHandler;
import com.authority.handler.JsonLogoutSuccessHandler;
import com.authority.source.DatabaseFilterInvocationSecurityMetadataSource;
import com.authority.user.FakePasswordEncoder;
import com.authority.user.FakeUserDetailsService;
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
public class BlogSecurityConfig extends WebSecurityConfigurerAdapter {
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
                Arrays.asList(new SupportAccessDecisionVoter(), new RoleAccessDecisionVoter()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(
                        new ObjectPostProcessor<FilterSecurityInterceptor>() {
                            @Override
                            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                                fsi.setAccessDecisionManager(accessDecisionManager());
                                fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
                                return fsi;
                            }
                        }
                )
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/loginSucc")
                //.loginProcessingUrl("/")
                //   .successForwardUrl("/api/loginSucc")
                .successHandler(new JsonAuthenticationSuccessHandler())
                .failureHandler(new JsonAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/")
                .logoutSuccessHandler(new JsonLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new JsonAccessDeniedHandler())
                .and().csrf().disable()
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true)
                .ignoring()
                .antMatchers("/images/**", "/js/**", "/css/**");
    }
}
