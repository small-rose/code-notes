package cn.com.lgs.factory_method;

/**
 * 海上物流
 *
 * @author luguosong
 * @date 2021/11/24 14:11
 */
public class SealLogistics extends Logistics{
    /**
     * @return 交通工具
     */
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
