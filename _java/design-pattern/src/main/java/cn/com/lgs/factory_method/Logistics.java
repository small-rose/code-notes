package cn.com.lgs.factory_method;

/**
 * 物流系统
 *
 * @author luguosong
 * @date 2021/11/24 14:05
 */
public abstract class Logistics {

    /**
     * 进行货物运送
     */
    public void planDeliver() {
        createTransport().deliver();
    }


    /**
     * 工厂方法
     *
     * 抽象方法，由子类实现，返回具体的交通工具
     *
     * @return 交通工具
     */
    public abstract Transport createTransport();
}
