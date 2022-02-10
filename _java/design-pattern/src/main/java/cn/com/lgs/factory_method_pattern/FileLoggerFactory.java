package cn.com.lgs.factory_method_pattern;

/**
 * 文件日志记录器工厂类，充当具体工厂
 *
 * @author luguosong
 * @date 2022/2/10 17:27
 */
public class FileLoggerFactory implements LoggerFactory {
    @Override
    public Logger createLogger() {
        //创建文件日志记录器对象
        Logger logger = new FileLogger();

        //创建文件
        //...

        return logger;
    }
}
