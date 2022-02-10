package cn.com.lgs.factory_method_pattern;

/**
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
