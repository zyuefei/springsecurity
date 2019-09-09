package com.portjs.base.secuity;

import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 15:39
 */

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
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("===================================用户登录:"+s);


        TUser user = userMapper.loginUserByAccount(s);


        return user;
    }
}
