package com.luguosong.creational.singleton_pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 负载均衡器类，充当单例角色
 *
 * @author 10545
 * @date 2022/3/28 23:28
 */
public class LoadBalancer {
    //私有静态成员变量，存储唯一实例
    private static LoadBalancer instance = null;

    //服务器集合
    private List serverList = null;

    //私有构造
    private LoadBalancer() {
        serverList = new ArrayList();
    }

    //公有静态成员方法，返回唯一实例
    public static LoadBalancer getLoadBalancer() {
        if (instance == null) {
            instance = new LoadBalancer();
        }
        return instance;
    }

    /**
     * 添加服务
     *
     * @param server
     */
    public void addServer(String server) {
        serverList.add(server);
    }

    /**
     * 删除服务
     *
     * @param server
     */
    public void removeServer(String server) {
        serverList.remove(server);
    }

    /**
     * 随机获取服务器
     *
     * @return
     */
    public String getServer() {
        Random random = new Random();
        int i = random.nextInt(serverList.size());
        return (String) serverList.get(i);
    }
}
