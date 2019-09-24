package com.portjs.base.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.portjs.base.dao.SpringSessionMapper;
import com.portjs.base.model.SpringSession;
/**
 * @author  zhangyuefei
 * @date  2019/9/19 6:53 下午
 * @version 1.0
 */
@Service
public class SpringSessionService extends ServiceImpl<SpringSessionMapper, SpringSession> {

}
