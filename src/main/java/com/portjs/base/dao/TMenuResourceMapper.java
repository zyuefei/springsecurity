package com.portjs.base.dao;

import com.alibaba.fastjson.JSONArray;import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portjs.base.model.TMenuResource;
import com.portjs.base.mpUtil.FBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/11 10:24 上午
 */
public interface TMenuResourceMapper extends FBaseMapper<TMenuResource> {

    List<TMenuResource> selectMenuByRoles(List<String> rids);

    List<TMenuResource> selectResByRoles(List<String> rids);

    /**
     * 过滤id为99开头的所有基础通用权限
     * @return
     */
    List<TMenuResource> selectAll();


    List<TMenuResource> selectAllByUserId(@Param("userId") String userId);

    TMenuResource selectLastByParent(@Param("parentId") String parentId);
}