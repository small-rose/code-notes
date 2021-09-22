package cn.com.lgs;

import java.util.stream.DoubleStream;

/**
 * @author luguosong
 * @date 2021/9/22 11:02
 */
public class Calculator {
    static double add(double... operands) {
        return DoubleStream.of(operands)
                .sum();
    }

    static double multiply(double... operands) {
        return DoubleStream.of(operands)
                .reduce(1, (a, b) -> a * b);
    }
}
