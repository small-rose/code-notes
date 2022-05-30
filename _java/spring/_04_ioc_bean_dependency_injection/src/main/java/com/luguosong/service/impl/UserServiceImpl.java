package com.luguosong.service.impl;

import com.luguosong.dao.UserDao;
import com.luguosong.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author luguosong
 * @date 2022/5/29 11:11
 */
public class UserServiceImpl implements UserService {

    //引用数据类型依赖
    //UserService依赖UserDao
    private UserDao userDao;

    //普通数据类型依赖
    private int number;

    //集合数据类型依赖
    private List<String> list;
    private Map<String, String> map;
    private Properties properties;


    /**
     * Spring可以通过有参构造进行依赖注入
     *
     * @param userDao
     * @param number
     * @param list
     */
    public UserServiceImpl(UserDao userDao, int number, List<String> list, Map<String, String> map, Properties properties) {
        System.out.println("有参构造");
        this.userDao = userDao;
        this.number = number;
        this.list = list;
        this.map = map;
        this.properties = properties;
    }

    public UserServiceImpl() {
        System.out.println("无参构造");
    }

    /**
     * Spring可以通过set方法进行依赖注入引用数据类型
     *
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Spring可以通过set方法进行依赖注入普通数据类型
     *
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Spring可以通过set方法进行依赖注入list集合
     *
     * @param list
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    /**
     * Spring可以通过set方法进行依赖注入map集合
     *
     * @param map
     */
    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    /**
     * Spring可以通过set方法进行依赖注入Properties
     *
     * @param properties
     */
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void hello() {
        System.out.println("注入引用数据类型：" + userDao.hello());
        System.out.println("注入基本数据类型：" + number);
        System.out.println("注入list集合：" + list);
        System.out.println("注入map集合：" + map);
        System.out.println("注入properties集合：" + properties);
        if (list != null) {
            System.out.println("list注入的对象类型是：" + list.getClass());
        }
        if (map != null) {
            System.out.println("map注入的对象类型是:" + map.getClass());
        }
        if (properties != null) {
            System.out.println("properties注入的对象类型是:" + properties.getClass());
        }
    }
}
