package com.luguosong._210_exceptions;

import org.junit.jupiter.api.Test;

/**
 * 异常
 *
 * @author luguosong
 * @date 2022/12/1
 */
public class ExceptionsDemo {
    @Test
    public void testThrow() throws ClassNotFoundException {
        try {
            Class.forName("AAA");
            System.out.println("异常后的方法");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("手动抛出非检查型异常");
        }finally {
            System.out.println("finally执行");
        }
        System.out.println("外部方法");
    }
}
