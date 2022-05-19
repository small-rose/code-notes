package com.luguosong.creational.factory_method_pattern;

/**
 * 工厂方法调用实例
 *
 * @author luguosong
 * @date 2022/2/10 17:35
 */
public class Demo {
    public static void main(String[] args) {
        LoggerFactory factory;
        Logger logger;
        factory = new FileLoggerFactory();
        logger = factory.createLogger();
        logger.writeLog();
    }
}
