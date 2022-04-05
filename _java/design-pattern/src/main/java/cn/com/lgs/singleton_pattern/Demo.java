package cn.com.lgs.singleton_pattern;

/**
 * 单例测试类
 *
 * @author 10545
 * @date 2022/3/28 23:45
 */
public class Demo {
    public static void main(String[] args) {
        //创建4个LoadBalancer对象
        LoadBalancer loadBalancer1 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer2 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer3 = LoadBalancer.getLoadBalancer();
        LoadBalancer loadBalancer4 = LoadBalancer.getLoadBalancer();

        //判断4个对象是否相同
        if (loadBalancer1 == loadBalancer2 && loadBalancer2 == loadBalancer3 && loadBalancer3 == loadBalancer4) {
            System.out.println("服务器负载均衡器具有唯一性");
        }

        //增加服务器
        loadBalancer1.addServer("Server 1");
        loadBalancer2.addServer("Server 2");
        loadBalancer3.addServer("Server 3");
        loadBalancer4.addServer("Server 4");

        //模拟客户端请求的分发
        for (int i = 0; i < 10; i++) {
            String server = loadBalancer1.getServer();
            System.out.println("分发请求至服务器：" + server);
        }

    }
}
