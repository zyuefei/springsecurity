package com.portjs.base.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:21
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String getUsers(){
        return "hello Spring security";
    }
}
