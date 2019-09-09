package com.portjs.base.secuity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:31
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MyAuthenticationProvider authenticationProvider;

    @Autowired
    private MyAccessDeniedHandlerimplements myAccessDeniedHandlerimplements;

    /**
     * 用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // TODO Auto-generated method stub
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/css/**", "/index").permitAll()
                    .antMatchers("/user/**").hasRole("USER")
                    .anyRequest().access("@rbacService.hasPermission(request,authentication)")//所有url权限过滤

                .and()
                .csrf().disable().cors() ;//关闭CSRF,允许跨域

//        未授权处理
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandlerimplements);


        http.formLogin().loginPage("/login_p")
                    .loginProcessingUrl("/login")
                    .successForwardUrl("/loginSuccessHandler")//登录成功之后转发处理
                    .failureForwardUrl("/loginFailureHandler")//登录失败之后转发处理
                    .permitAll();
    }

}