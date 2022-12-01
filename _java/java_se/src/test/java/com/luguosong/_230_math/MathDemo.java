package com.luguosong._230_math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author luguosong
 * @date 2022/11/29
 */
public class MathDemo {
    /**
     * 高精度计算
     */
    @Test
    public void testBigDecimal() {
        System.out.println("不使用精确计算无法正确的表示小数：" + 0.7 * 0.7);
        System.out.println("精度计算：" + new BigDecimal("0.7").multiply(new BigDecimal("0.7")));
    }
}
