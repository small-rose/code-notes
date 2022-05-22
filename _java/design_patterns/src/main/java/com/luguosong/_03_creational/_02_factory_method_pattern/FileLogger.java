package com.luguosong._03_creational._02_factory_method_pattern;

/**
 * 文件日志记录器，充当具体产品角色
 *
 * @author luguosong
 * @date 2022/2/10 17:11
 */
public class FileLogger implements Logger{
    @Override
    public void writeLog() {
        System.out.println("文件记录日志");
    }
}
