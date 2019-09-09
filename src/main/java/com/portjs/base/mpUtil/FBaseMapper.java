package com.portjs.base.mpUtil;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FBaseMapper<T> extends BaseMapper<T> {

    IPage<T> fSelectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

    IPage<Map<String, Object>> fSelectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
}
