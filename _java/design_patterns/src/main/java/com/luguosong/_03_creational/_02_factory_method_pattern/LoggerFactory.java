package com.luguosong._03_creational._02_factory_method_pattern;

/**
 * 日志记录器工厂接口，充当抽象工厂角色
 *
 * @author luguosong
 * @date 2022/2/10 17:17
 */
public interface LoggerFactory {
    public Logger createLogger();  //抽象工厂方法
}
