package com.luguosong._03_creational.factory_method_pattern;

/**
 * 数据库记录日志器，充当具体产品
 *
 * @author luguosong
 * @date 2022/2/10 17:08
 */
public class DatabaseLogger implements Logger{
    @Override
    public void writeLog() {
        System.out.println("数据库日志记录。");
    }
}
