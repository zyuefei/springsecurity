package com.portjs.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.portjs.base.aop.LogInfo;
import com.portjs.base.aop.LogModel;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.model.TUser;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.service.TUserService;
import com.portjs.base.util.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/6 14:21
 */
@LogModel("用户啦啦啦")
@Api(tags = "用户管理接口")
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

    @LogInfo(model = "方法级别：用户管理", action = "用户新增" ,param = "'用户新增'+#user.account")
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
        int index = userService.update(user);
        return new ResponseMessage(index);
    }

    @LogInfo(model = "方法级别：用户管理", action = "用户删除",param = "'用户id：'+#ids")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "ids", value = "主键ids", required = true)
    @PostMapping("/delete")
    @Override
    public ResponseMessage delete(@RequestBody JSONArray ids){
        List<String> idList =  JSON.parseArray(ids.toJSONString(),String.class);
        int index = userService.deletes(idList);
        return success(index);
    }

    @LogInfo(model = "方法级别：用户管理", action = "更新用户状态",param = "'用户id：'+#user.id+'enable:'+#user.enable")
    @ApiOperation("更新用户状态")
    @PostMapping("/updateUserState")
    public ResponseMessage updateUserState(@RequestBody TUser user){
        userMapper.updateEnableById(user.getEnable(),user.getId());
        return success();
    }


}
