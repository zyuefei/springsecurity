package com.portjs.base.controller;

import com.portjs.base.model.TUser;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:39
 */
@Slf4j
@RestController
public class HomeController {


    @RequestMapping({"/whoim","/whoim2"})
    public Object whoim(){
        TUser u = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u;
    }


    @RequestMapping({"/","/index"})
    public String index(){
        return "this is index";
    }

    @RequestMapping("/login_p")
    public String loginP(){
        return "未经授权，请登录";
    }

    @RequestMapping("/loginError")
    public String loginError(){
        return "登录失败,loginError";
    }

    /**
     * 登录成功重定向
     * @param request
     * @return
     */
    @RequestMapping("/loginSuccessHandler")
    public String loginSuccessHandler(HttpServletRequest request){
        String username = request.getParameter("username");
        log.info(username+" login success");
        //用户登录成功信息
        TUser u = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return username+"登录成功"+u.toString();
    }


    /**
     * 登录失败重定向
     * @param request
     * @return
     */
    @RequestMapping("/loginFailureHandler")
    public String loginFailureHandler(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        log.info(username+" login failure");
        return username+"登录失败";
    }



}
