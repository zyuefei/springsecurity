package com.portjs.base.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.portjs.base.aop.LogInfo;
import com.portjs.base.dao.TMenuResourceMapper;
import com.portjs.base.exception.UnifiedExceptionHandler;
import com.portjs.base.model.TMenuResource;
import com.portjs.base.mpUtil.FBaseMapper;
import com.portjs.base.service.MenuResourcService;
import com.portjs.base.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 18:51
 */
@RestController
@RequestMapping("menu-resource")
public class MenuResourceController extends BaseController<TMenuResource> {

    @Autowired
    private MenuResourcService menuResourcService;

    @Autowired
    private TMenuResourceMapper tMenuResourceMapper;

    @RequestMapping("select-menu-resource-roles")
    @ResponseBody
    public ResponseMessage selectMenuAndResourceByRoles(@RequestBody JSONArray jsonArray){
        //统一异常拦截
        JSONObject json = menuResourcService.selectMenuAndResourceByRoles(jsonArray);
        return success(json);
    }

    @Override
    public FBaseMapper<TMenuResource> getMapper() {
        return tMenuResourceMapper;
    }
}
