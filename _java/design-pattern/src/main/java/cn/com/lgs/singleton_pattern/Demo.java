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
    }
}
