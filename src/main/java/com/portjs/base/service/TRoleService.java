package com.portjs.base.service;

import com.portjs.base.dao.TRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portjs.base.dao.TRoleMapper;
import com.portjs.base.model.TRole;
/**
 * @author  zhangyuefei
 * @date  2019/9/11 10:34 上午
 * @version 1.0
 */
@Service
public class TRoleService extends ServiceImpl<TRoleMapper, TRole> {

    @Autowired
    private TRoleMenuMapper tRoleMenuMapper;

    public int addPermission(String roleId, List<String> permissions) {
       int index = tRoleMenuMapper.insertList(roleId,permissions);
       return index;
    }
}
