package com.portjs.base.dao;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portjs.base.model.TMenuResource;
import com.portjs.base.mpUtil.FBaseMapper;

import java.util.List;

public interface TMenuResourceMapper extends FBaseMapper<TMenuResource> {

    List<TMenuResource> selectByRoles(JSONArray roles);
}