package cn.com.lgs.factory_method;

/**
 * @author luguosong
 * @date 2021/11/24 14:02
 */
public class Ship implements Transport{
    /**
     * 运送接口
     */
    @Override
    public void deliver() {
        System.out.println("轮船运送货物");
    }
}
