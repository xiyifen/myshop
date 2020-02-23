package com.xiyifen.myshop.system.service.impl;

import com.xiyifen.myshop.system.entity.User;
import com.xiyifen.myshop.system.mapper.UserMapper;
import com.xiyifen.myshop.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author xiyifen
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
