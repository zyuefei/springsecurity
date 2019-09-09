package com.portjs.base.secuity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @Author:zhangyuefei
 * @Date:2019/9/6 17:20
 */

@Component
public class MyAccessDeniedHandlerimplements implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)throws IOException, ServletException {

//返回json形式的错误信息
        response.setCharacterEncoding("UTF-8");

        response.setContentType("application/json");

        response.getWriter().println("{\"code\":403,\"message\":\"没有权限访问呀！\",\"data\":\"\"}");

        response.getWriter().flush();

    }

}