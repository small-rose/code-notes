package com.luguosong._04_structural._07_proxy_pattern;

/**
 * 日志记录类
 * @author luguosong
 * @date 2022/5/27 16:52
 */
public class Logger {
    public void log(String userId){
        System.out.println("更新数据库，用户'"+userId+"'查询次数加1！");
    }
}
