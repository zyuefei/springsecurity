package com.portjs.base.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.portjs.base.dao.TMenuResourceMapper;
import com.portjs.base.model.TMenuResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 18:54
 */
@Slf4j
@Service
public class MenuResourcService {

    @Autowired
    private TMenuResourceMapper tMenuResourceMapper;

    public JSONObject selectMenuAndResourceByRoles(JSONArray roles){
        List<TMenuResource> tMenuResources = tMenuResourceMapper.selectByRoles(roles);
        TMenuResource rootMenu = tMenuResources.get(0);//默认查询出来的第一个节点是根节点
        rootMenu.setChildren(getChildrenList(rootMenu.getId(),tMenuResources));
        String jsonObject = JSON.toJSONString(rootMenu, SerializerFeature.WriteMapNullValue);
       return JSON.parseObject(jsonObject);
    }


    //递归遍历树
    private List<TMenuResource> getChildrenList(String pid, List<TMenuResource> list) {
        List<TMenuResource> tree = new ArrayList<>();
        for (TMenuResource tMenuResource:list){
            if (pid.equals(tMenuResource.getParentId())){
                tree.add(tMenuResource);
                list.remove(tMenuResource);
                tMenuResource.setChildren(getChildrenList(tMenuResource.getId(),list));
            }
        }
        return tree;
    }
}
