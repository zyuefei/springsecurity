package com.portjs.base.dao;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portjs.base.model.TRoleMenu;
import com.portjs.base.mpUtil.FBaseMapper;

import java.util.List;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/18 2:37 下午
 */
public interface TRoleMenuMapper extends FBaseMapper<TRoleMenu> {
    int insertList(@Param("roleId") String roleId,@Param("list") List<String> list);



}