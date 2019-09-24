package com.portjs.base.controller;

import com.portjs.base.dao.SpringSessionMapper;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:39
 */
@Slf4j
@RestController
public class HomeController {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private SpringSessionMapper springSessionMapper;

    @Value("${isConcurrent}")
    private String isConcurrent;
    @Value("${wrongCounts}")
    private int wrongCounts;


    @RequestMapping({"/whoim","/whoim2"})
    public Object whoim(){
        TUser u = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return u;
    }


    @RequestMapping({"/","/index"})
    public String index(){
        TUser tUser = tUserMapper.loginUserByAccount("admin");
        return "this is index"+tUser;
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
    public ResponseMessage loginSuccessHandler(HttpServletRequest request){
        String username = request.getParameter("username");
        log.info(username+" login success");
        //用户登录成功信息
        TUser user = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        ResponseMessage responseMessage = new ResponseMessage(CodeEnum.SUCCESS.getCode(),"登录成功", user);
        //判断是否需要顶掉他人登录
        if (isConcurrent.equals("1")) {
            springSessionMapper.deleteById(username);
        }
        //登录成功清
        user.setWrongCount(0);
        user.setLastLoginTime(new Date());
        tUserMapper.updateById(user);


        return responseMessage;
    }


//    /**
//     * 登录失败重定向
//     * @param request
//     * @return
//     */
//    @RequestMapping("/loginFailureHandler")
//    public ResponseMessage loginFailureHandler(HttpServletRequest request){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        log.info(username+" login failure");
//
//        ResponseMessage responseMessage = null;
//        TUser user = tUserMapper.loginUserByAccount(username);
//        //密码输入错误次数+1
//        Integer wrongCount = user.getWrongCount();
//        wrongCount++;
//        if (wrongCount<wrongCounts){
//            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "密码输入错误!您还剩" + (wrongCounts - wrongCount) + "次登录机会","");
//        }
//        if (wrongCount == wrongCounts) {
//            //输入密码错误三次 冻结账号
//
//            user.setUnlockTime(new Date(System.currentTimeMillis()+(5*60000)));
//            responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请5分钟后重试","");
//        }
//        if (wrongCount>wrongCounts){
//            int i = wrongCount - wrongCounts;
//           switch (i){
//               case 1:
//                   user.setUnlockTime(new Date(System.currentTimeMillis()+(30*60000)));
//                   responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请30分钟后重试","");
//                   break;
//               case 2:
//                   user.setUnlockTime(new Date(System.currentTimeMillis()+(60*60000)));
//                   responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请1小时后重试","");
//                   break;
//               case 3:
//                   user.setUnlockTime(new Date(System.currentTimeMillis()+(24*60*60000)));
//                   responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请1天后重试","");
//                   break;
//                   default:
//                       user.setLock(0);
//                       responseMessage = new ResponseMessage(ResultCodeEnum.ERROR.getCode(), "账户被锁定，请联系管理员","");
//                       break;
//           }
//        }
//        user.setWrongCount(wrongCount);
//
//        tUserMapper.updateById(user);
//        return responseMessage;
//    }



}
