package com.portjs.base.dao;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.portjs.base.model.TUser;
import com.portjs.base.mpUtil.FBaseMapper;
import org.springframework.stereotype.Component;


public interface TUserMapper extends FBaseMapper<TUser> {
    //登录验证
    TUser loginUserByAccount(String account);

    int updateEnableById(@Param("updatedEnable")Integer updatedEnable,@Param("id")String id);


}