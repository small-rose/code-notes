package com.luguosong.service.impl;

import com.luguosong.dao.RoleDao;
import com.luguosong.dao.UserDao;
import com.luguosong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author luguosong
 * @date 2022/5/30 14:44
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * @Autowired+@Qualifier=@Resource
     */
    @Autowired
    @Qualifier("roleDaoImpl")
    private RoleDao roleDao;

    @Override
    public void hello() {
        System.out.println("用户名："+userDao.getName()+",角色："+roleDao.getRole());
    }
}
