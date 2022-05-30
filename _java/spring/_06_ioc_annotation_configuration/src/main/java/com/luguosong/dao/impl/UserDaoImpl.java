package com.luguosong.dao.impl;

import com.luguosong.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * @author luguosong
 * @date 2022/5/30 14:39
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public String getName() {
        return "张三";
    }
}
