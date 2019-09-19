package com.portjs.base.dao;
import com.portjs.base.model.TRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portjs.base.model.TUserRole;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/18 2:38 下午
 */
public interface TUserRoleMapper extends BaseMapper<TUserRole> {

    int insertList(@Param("userId")String userId,@Param("list")List<TRole> list);


}