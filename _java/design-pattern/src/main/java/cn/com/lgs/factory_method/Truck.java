package cn.com.lgs.factory_method;

/**
 * @author luguosong
 * @date 2021/11/24 14:01
 */
public class Truck implements Transport{

    /**
     * 运送接口
     */
    @Override
    public void deliver() {
        System.out.println("卡车运送货物");
    }
}
