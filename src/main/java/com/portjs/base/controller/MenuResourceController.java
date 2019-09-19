package com.portjs.base.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.portjs.base.dao.TMenuResourceMapper;
import com.portjs.base.model.TMenuResource;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.service.MenuResourcService;
import com.portjs.base.util.ResponseMessage;
import com.portjs.base.util.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 18:51
 */
@Slf4j
@RestController
@RequestMapping("menu-resource")
public class MenuResourceController extends BaseController<TMenuResource>{

    @Autowired
    private MenuResourcService menuResourcService;

    @Autowired
    private TMenuResourceMapper tMenuResourceMapper;

    @Override
    public FBaseMapper<TMenuResource> getMapper() {
        return tMenuResourceMapper;
    }

    /**
     * 根据角色id查询菜单
     * @return
     */
    @PostMapping("select-menu-resource-roles")
    public ResponseMessage selectMenuAndResourceByRoles(@RequestBody List<String> rids){
        JSONObject menutree = menuResourcService.selectMenuByRoles(rids);
        List<TMenuResource> list = menuResourcService.selectResourceByRoles(rids);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("menu",menutree);
        jsonObject.put("resources",list);
        return success(jsonObject);
    }

    /**
     * 获取当前用户下的所有菜单和权限
     *
     * @return
     */
    @GetMapping("/select-menu")
    public ResponseMessage selectMenu(){
        TMenuResource tree = menuResourcService.selectMenuAndResource();
        return success(tree);
    }

    @PostMapping("/save")
    @Override
    public ResponseMessage save(TMenuResource tMenuResource){
        int index = menuResourcService.save(tMenuResource);
        return success(index);
    }




}
