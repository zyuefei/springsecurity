package com.portjs.base.secuity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 17:02
 */

public class MyAuthFailureHandler implements AuthenticationFailureHandler {

    @Value("${wrongCounts}")
    private int wrongCounts;
    @Autowired
    private TUserMapper userMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        ResponseMessage responseMessage = null;
        if (e instanceof BadCredentialsException) {
            String username = req.getParameter("username");
            TUser user = userMapper.loginUserByAccount(username);
            System.out.println(user);
            //密码输入错误次数+1
            Integer wrongCount = user.getWrongCount();
            wrongCount++;
            if (wrongCount == wrongCounts) {
                //输入密码错误三次 冻结账号
                user.setEnable(0);
            }
            user.setWrongCount(wrongCount);
            userMapper.updateById(user);
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "密码输入错误!您还剩" + (wrongCounts - wrongCount) + "次登录机会","");
        } else if (e instanceof LockedException) {
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请联系管理员!","");
        } else if (e instanceof CredentialsExpiredException) {
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "密码过期，请联系管理员!","");
        } else if (e instanceof AccountExpiredException) {
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账号过期，请联系管理员!","");
        } else if (e instanceof DisabledException) {
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请联系管理员!","");
        } else {
            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "该用户不存在!","");
        }
        resp.setStatus(401);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(responseMessage));
        out.flush();
        out.close();
    }
}
