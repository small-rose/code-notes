package cn.com.lgs.factory_method;

/**
 * 测试工厂方法
 *
 * @author luguosong
 * @date 2021/11/24 14:12
 */
public class Demo {
    public static void main(String[] args) {
        //陆地物流
        Logistics roadLogistics = new RoadLogistics();
        roadLogistics.planDeliver();

        //海上物流
        Logistics sealLogistics = new SealLogistics();
        sealLogistics.planDeliver();
    }
}
