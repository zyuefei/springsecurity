package com.portjs.base.service;

import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.portjs.base.dao.TUserMapper;
import com.portjs.base.dao.TUserRoleMapper;
import com.portjs.base.model.TRole;
import com.portjs.base.model.TUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhangyuefei
 * @version 1.0
 * @date 2019/9/18 5:19 下午
 */
@Slf4j
@Service
public class TUserService {

    @Autowired
    private TUserRoleMapper userRoleMapper;

    @Autowired
    private TUserMapper userMapper;

    @Transactional
    public int save(TUser user) {
        int index = userMapper.insert(user);
        List<TRole> roles = user.getRoles();
        index = userRoleMapper.insertList(user.getId(),roles);
        return index;
    }

    @Transactional
    public int update(TUser user) {
        int index = userMapper.updateById(user);
        userRoleMapper.deleteByUserId(user.getId());
        List<TRole> roles = user.getRoles();
        index = userRoleMapper.insertList(user.getId(),roles);
        return index;
    }

    public int deletes(List<String> idList) {
        int index = userMapper.deleteBatchIds(idList);
        index = userRoleMapper.deleteByUserIds(idList);
        return index;
    }
}
