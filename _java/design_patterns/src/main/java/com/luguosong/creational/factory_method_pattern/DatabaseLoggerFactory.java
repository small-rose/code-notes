package com.luguosong.creational.factory_method_pattern;

/**
 * 数据库日志记录器工厂类，充当具体工厂
 *
 * @author luguosong
 * @date 2022/2/10 17:20
 */
public class DatabaseLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        //连接数据库
        //...

        //创建数据库日志记录器对象
        Logger logger = new DatabaseLogger();

        //初始化数据库日志记录器
        //...

        return logger;
    }
}
