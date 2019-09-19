package com.portjs.base.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.portjs.base.dao.TMenuResourceMapper;
import com.portjs.base.model.TMenuResource;
import com.portjs.base.model.TUser;
import com.portjs.base.util.ResponseMessage;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:zhangyuefei
 * @Date:2019/9/9 18:54
 */
@Slf4j
@Service
public class MenuResourcService {

    @Autowired
    private TMenuResourceMapper tMenuResourceMapper;

    public JSONObject selectMenuByRoles(List<String> roles) {
        List<TMenuResource> tMenuResources = tMenuResourceMapper.selectMenuByRoles(roles);
        TMenuResource rootMenu = getRoot(tMenuResources);//默认查询出来的第一个节点是根节点
        rootMenu.setChildren(getChildrenList(rootMenu.getId(), tMenuResources));
        String jsonObject = JSON.toJSONString(rootMenu, SerializerFeature.WriteMapNullValue);
        return JSON.parseObject(jsonObject);
    }

    public List<TMenuResource> selectResourceByRoles(List<String> roles) {
        List<TMenuResource> tMenuResources = tMenuResourceMapper.selectResByRoles(roles);
        return tMenuResources;
    }


    /**
     * 获取资源树的根节点
     *
     * @param list
     * @return
     */
    private TMenuResource getRoot(List<TMenuResource> list) {
        TMenuResource tMenuResource = list.get(0);
        if (StringUtils.isEmpty(tMenuResource.getParentId())) {
            return tMenuResource;
        }
        HashMap<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            TMenuResource temp = list.get(i);
            String id = temp.getId();
            String pid = temp.getParentId();
            if (countMap.get(id) == null) {
                countMap.put(id, 1);
            } else {
                countMap.put(id, countMap.get(id) + 1);
            }
            if (countMap.get(pid) == null) {
                countMap.put(pid, 1);
            } else {
                countMap.put(pid, countMap.get(pid) + 1);
            }
        }
        String parentId = "";
        //找出只出现一次的父节点的parentid
        for (String id : countMap.keySet()) {
            if (countMap.get(id) == 1) {
                parentId = id;
                break;
            }
        }
        //找出根节点对象
        for (TMenuResource tMenuResource1 : list) {
            if (tMenuResource1.getParentId().equals(parentId)) {
                return tMenuResource1;
            }
        }
        return tMenuResource;
    }


    //递归遍历树
    private List<TMenuResource> getChildrenList(String pid, List<TMenuResource> list) {
        List<TMenuResource> tree = new ArrayList<>();
        for (TMenuResource tMenuResource : list) {
            if (pid.equals(tMenuResource.getParentId())) {
                tree.add(tMenuResource);
                list.remove(tMenuResource);
                tMenuResource.setChildren(getChildrenList(tMenuResource.getId(), list));
            }
        }
        return tree;
    }

    public TMenuResource selectMenuAndResource() {
        Set<String> roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities());
        TUser user = (TUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TMenuResource> list;
        if (roles.contains("ROLE_admin")) {
            list = tMenuResourceMapper.selectAll();
        }else {
            list = tMenuResourceMapper.selectAllByUserId(user.getId());
        }
        TMenuResource rootMenu = getRoot(list);
        rootMenu.setChildren(getChildrenList(rootMenu.getId(),list));
        return rootMenu;
    }

    public int save(TMenuResource tMenuResource) {
        TMenuResource lastMenu = tMenuResourceMapper.selectLastByParent(tMenuResource.getParentId());
        String addId="";
        if (lastMenu !=null) {
            int numSize = lastMenu.getId().length();
            int id = Integer.parseInt(lastMenu.getId());
            id = id + 1;
            addId = String.valueOf(id);
            if (numSize > addId.length()) {
                addId = "0" + addId;
            }
            tMenuResource.setSort(lastMenu.getSort()+1);
        }else {
            addId = tMenuResource.getParentId()+"01";
            tMenuResource.setSort(0);
        }
        //0为菜单 ， 1为资源
        tMenuResource.setId(addId);
       return tMenuResourceMapper.insert(tMenuResource);
    }
}
