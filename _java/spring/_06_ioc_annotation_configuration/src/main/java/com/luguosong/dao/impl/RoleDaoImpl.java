package com.luguosong.dao.impl;

import com.luguosong.dao.RoleDao;
import org.springframework.stereotype.Repository;

/**
 * @author luguosong
 * @date 2022/5/30 14:40
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl implements RoleDao {
    @Override
    public String getRole() {
        return "普通用户";
    }
}
