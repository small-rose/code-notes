package cn.com.lgs.factory_method;

/**
 * 陆地物流
 *
 * @author luguosong
 * @date 2021/11/24 14:09
 */
public class RoadLogistics extends Logistics{
    /**
     * @return 交通工具
     */
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}
