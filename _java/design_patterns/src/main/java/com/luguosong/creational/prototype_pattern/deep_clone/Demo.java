package com.luguosong.creational.prototype_pattern.deep_clone;

/**
 * 客户端
 *
 * @author 10545
 * @date 2022/3/24 23:33
 */
public class Demo {
    public static void main(String[] args) {
        WeeklyLog log_previous, log_new = null;
        log_previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        try {
            log_new = log_previous.deepClone();
        } catch (Exception e) {
            System.out.println("克隆失败");
        }
        //比较周报
        System.out.println("周报是否相同：" + (log_previous == log_new));
        //比较附件
        System.out.println("附件是否相同："+(log_previous.getAttachment()==log_new.getAttachment()));
    }
}
