package com.portjs.base.controller;

import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.service.TUserService;
import com.portjs.base.util.ResponseMessage;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:21
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<TUser>{


    @Autowired
    private TUserService userService;

    @Autowired
    private TUserMapper userMapper;

    @Override
    public FBaseMapper<TUser> getMapper() {
        return userMapper;
    }

    @ApiOperation("新增")
    @PostMapping("/save")
    @Override
    public ResponseMessage save(@RequestBody TUser user){
        int index = userService.save(user);
        return new ResponseMessage(index);
    }

    @ApiOperation("修改")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    @Override
    public ResponseMessage saveOrUpdate(@RequestBody TUser user){

        return null;
    }


}
