package com.portjs.base.secuity;

import com.portjs.base.aop.LogInfo;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 15:39
 */
@Api("啦啦啦")
@Component
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private TUserMapper userMapper;
    /**
     * 登录验证
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @LogInfo(param = "'用户'+#s+'登录'")
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("===================================用户登录:"+s);


        TUser user = userMapper.loginUserByAccount(s);

        return user;
    }
}
