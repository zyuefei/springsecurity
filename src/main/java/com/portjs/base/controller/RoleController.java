package com.portjs.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.portjs.base.dao.TRoleMapper;
import com.portjs.base.model.TRole;
import com.portjs.base.model.TRoleMenu;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.service.TRoleService;
import com.portjs.base.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/18 4:46 下午
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<TRole>{

    @Autowired
    private TRoleMapper tRoleMapper;

    @Autowired
    private TRoleService tRoleService;

    @Override
    public FBaseMapper<TRole> getMapper() {
        return tRoleMapper;
    }

    /**
     * 设置权限
     *
     * @param json
     * @return
     */
    @PostMapping("/insert-permit")
    public ResponseMessage setRolePermission(@RequestBody JSONObject json){
        List<String> permissions = JSON.parseArray(json.getJSONArray("list").toJSONString(),String.class);
        String roleId = json.getString("object");
        int index = tRoleService.addPermission(roleId,permissions);
        return success(index);
    }
}
