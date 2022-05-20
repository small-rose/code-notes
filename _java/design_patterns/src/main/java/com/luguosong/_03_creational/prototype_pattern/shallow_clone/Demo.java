package com.luguosong._03_creational.prototype_pattern.shallow_clone;

/**
 * 客户端
 *
 * @author 10545
 * @date 2022/3/24 22:08
 */
public class Demo {
    public static void main(String[] args) {
        WeeklyLog log_previous, log_new;
        //这里主要讨论原型模式，不考虑开闭原则，因此直接使用new
        log_previous = new WeeklyLog();
        Attachment attachment = new Attachment();
        log_previous.setAttachment(attachment);
        log_new = log_previous.clone();
        //==比较的是地址，因此不相同
        System.out.println("周报是否相同：" + (log_previous == log_new));
        //因为是软克隆，因此附件相同
        System.out.println("附件是否相同：" + (log_previous.getAttachment() == log_new.getAttachment()));
    }
}
