package com.luguosong.design_patterns._04_behavioral._09_strategy_pattern;

import java.util.Scanner;

/**
 * 策略模式示例
 *
 * @author luguosong
 * @date 2022/12/20
 */
public class StrategyDemo {
    /**
     * 抽象算法类
     */
    static abstract class Strategy {
        public abstract void algorithmInterface();
    }

    /**
     * 具体算法A
     */
    static class ConcreteStrategyA extends Strategy {
        @Override
        public void algorithmInterface() {
            System.out.println("算法A");
        }
    }

    /**
     * 具体算法B
     */
    static class ConcreteStrategyB extends Strategy {
        @Override
        public void algorithmInterface() {
            System.out.println("算法B");
        }
    }

    /**
     * 具体算法C
     */
    static class ConcreteStrategyC extends Strategy {
        @Override
        public void algorithmInterface() {
            System.out.println("算法C");
        }
    }

    /**
     * 上下文
     *
     * 上下文并不执行任务，而是将工作委派给已连接的策略对象。
     *
     */
    static class Context {
        // 这里可以使用多种方法进行依赖注入，比如构造，简单工厂或setter方法
        Strategy strategy;

        /**
         * 使用构造方法进行依赖注入
         *
         * @param strategy
         */
        public Context(Strategy strategy) {
            this.strategy = strategy;
        }

        //上下文接口
        public void contextInterface() {
            strategy.algorithmInterface();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do {
            System.out.print("请输入策略类型：");
            s = scanner.nextLine();
            Strategy strategy = null;
            switch (s) {
                case "A":
                    strategy = new ConcreteStrategyA();
                    break;
                case "B":
                    strategy = new ConcreteStrategyB();
                    break;
                case "C":
                    strategy = new ConcreteStrategyC();
                    break;
                default:
                    System.out.println("算法类型错误");
            }
            if (strategy!=null){
                new Context(strategy).contextInterface();
            }
        }
        while (!"exit".equals(s));
    }
}
