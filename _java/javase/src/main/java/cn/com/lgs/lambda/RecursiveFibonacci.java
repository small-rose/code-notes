package cn.com.lgs.lambda;

/**
 * 使用Lambda的递归计算斐波拉契数
 *
 * @author luguosong
 * @date 2022/3/3 11:13
 */

interface IntCall {
    int call(int arg);
}

public class RecursiveFibonacci {
    IntCall fib;

    //在构造中使用Lambda实例化fib变量
    RecursiveFibonacci() {
        fib = n ->
                n == 0 ? 0 : n == 1 ? 1 : fib.call(n - 1) + fib.call(n - 2);
    }

    int fibonacci(int n){
        return fib.call(n);
    }

    public static void main(String[] args) {
        RecursiveFibonacci rf = new RecursiveFibonacci();
        System.out.println(rf.fibonacci(20));
    }
}
