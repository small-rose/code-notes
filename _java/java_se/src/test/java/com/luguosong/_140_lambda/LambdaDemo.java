package com.luguosong._140_lambda;

import org.junit.jupiter.api.Test;

/**
 * @author luguosong
 * @date 2022/11/25
 */
public class LambdaDemo {
    interface Runnable {
        void run();
    }

    /**
     * 匿名类测试
     */
    @Test
    public void testAnonymousClasses() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("run");
            }
        };
        runnable.run();
    }


    /**
     * 计算末段代码的执行时间
     *
     * @param r
     */
    private void executionTime(Runnable r) {
        long start = System.currentTimeMillis();
        r.run();
        long end = System.currentTimeMillis();
        System.out.println("代码执行时间：" + (end - start));
    }

    /**
     * 匿名类用来传递代码
     */
    @Test
    public void testCodeTransfer() {
        executionTime(new Runnable() {
            @Override
            public void run() {
                String s = "";
                for (int i = 0; i < 10000; i++) {
                    s += i;
                }
            }
        });
    }

    interface Callback {
        //成功回调
        void success();

        //失败回调
        void fail();
    }

    /**
     * 模拟请求调用
     *
     * @param url
     * @param callback
     */
    private void get(String url, Callback callback) {
        boolean ret = true;
        if (ret) {
            callback.success();
        } else {
            callback.fail();
        }
    }

    /**
     * 匿名类用于回调
     */
    @Test
    public void testCallBack() {
        get("xxx", new Callback() {
            @Override
            public void success() {
                System.out.println("请求成功");
            }

            @Override
            public void fail() {
                System.out.println("请求失败");
            }
        });
    }


    public interface Filter {
        boolean accept(String filename);
    }

    private static void getAllFilenames(String dir, Filter filter) {
        //获取所有文件名集合
        //File file = new File(dir);
        //String[] list = file.list();

        String[] list = {"aaa.jpg", "bbb.dwg", "ccc.dwg"};
        //过滤适合的文件名
        for (String filename : list) {
            if (filter.accept(filename)) {
                System.out.println(filename);
            }
        }
    }

    /**
     * 匿名类用作过滤器
     */
    @Test
    public void testFilter() {
        getAllFilenames("E:/", new Filter() {
            @Override
            public boolean accept(String filename) {
                return filename.contains(".dwg");
            }
        });
    }
}
