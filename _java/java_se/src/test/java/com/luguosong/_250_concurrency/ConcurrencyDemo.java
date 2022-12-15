package com.luguosong._250_concurrency;

import org.junit.jupiter.api.Test;

/**
 * @author luguosong
 * @date 2022/12/13
 */
public class ConcurrencyDemo {

    /**
     * 开启新线程，方式一
     */
    @Test
    public void testNewThread1() {
        //创建新线程
        Thread thread = new Thread(() -> {
            System.out.println("线程：" + Thread.currentThread().getName());
        });

        //启动线程
        thread.start();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("线程：" + Thread.currentThread().getName());
        }
    }

    /**
     * 开启新线程，方式二
     */
    @Test
    public void testNewThread2() {
        //创建线程对象
        Thread thread = new MyThread();
        //启动线程
        thread.start();
    }

    /**
     * sleep和interrupt
     */
    @Test
    public void testThreadState() {
        Thread thread = new Thread(() -> {
            System.out.println(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(2);
        });
        //开启多线程
        thread.start();

        System.out.println(3);

        //中断线程
        thread.interrupt();
    }

    /**
     * join方法
     */
    @Test
    public void testJoin() {
        Thread thread = new Thread(() -> {
            System.out.println(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(2);
        });
        thread.start();
        try {
            //等线程thread执行完，当前线程再继续执行任务
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(3);
    }

    static int tickets = 100;

    /**
     * 不考虑线程安全问题进行卖票
     */
    @Test
    public void testThreadSafe1() {
        for (int i = 1; i <= 4; i++) {
            Thread thread = new Thread(() -> {
                String name = Thread.currentThread().getName();
                while (tickets > 0) {
                    System.out.println(name + "卖出一张票,还剩：" + --tickets);
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }

    /**
     * 同步语句解决线程安全问题
     */
    @Test
    public void testThreadSafe2() {

        for (int i = 1; i <= 4; i++) {
            //这里四次创建线程，同时创建了四个Runnable对象，因此不能用Runnable对象的内部锁
            Thread thread = new Thread(() -> {
                String name = Thread.currentThread().getName();
                while (tickets > 0) {
                    //表示这段代码同一时间只能有一个线程在操作
                    //这里this表示当前测试类，而不是Lambda创建的Runnable对象
                    //synchronized (this)表示只有拿到测试类对象内部锁的线程才能执行其中代码
                    //要确保多个线程使用同一个对象的内部锁才能保证线程安全
                    synchronized (this) {
                        if (tickets < 1) break;
                        System.out.println(name + "卖出一张票,还剩：" + --tickets);
                    }
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }

    private synchronized void sell() {
        String name = Thread.currentThread().getName();
        if (tickets < 1) return;
        System.out.println(name + "卖出一张票,还剩：" + --tickets);
    }

    /**
     * 同步方法解决线程安全问题
     */
    @Test
    public void testThreadSafe3() {
        for (int i = 1; i <= 4; i++) {
            //这里四次创建线程，同时创建了四个Runnable对象，因此不能用Runnable对象的内部锁
            Thread thread = new Thread(() -> {
                while (tickets > 0) {
                    sell();
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }

    class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }

        public synchronized void hello(Person person) {
            System.out.format("[%s] hello to [%s]\n", name, person.name);
            try {
                //确保第二个线程锁被占用，保证死锁
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            person.smile(this);
        }

        public synchronized void smile(Person person) {
            System.out.format("[%s] smile to [%s]\n", name, person.name);
        }
    }

    /**
     * 模拟死锁情况
     */
    @Test
    public void testDeadlock() {
        Person zs = new Person("张三");
        Person ls = new Person("李四");
        new Thread(() -> {
            zs.hello(ls);
        }).start();
        new Thread(() -> {
            ls.hello(zs);
        }).start();
    }

    static boolean hasFood = false;

    /**
     * 测试线程间通讯
     */
    @Test
    public void testCommunication() {
        //制作食物线程
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                //必须拿到线程锁，才能调用wait()和notifyAll()
                synchronized ("1") {
                    while (hasFood) {
                        try {
                            //释放"1"内部锁，线程进入WAITING状态
                            "1".wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    hasFood = true;
                    System.out.println("食物做好了" + i);
                    "1".notifyAll();
                }
            }
        });
        thread1.start();

        //吃食物线程
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                //必须拿到线程锁，才能调用wait()和notifyAll()
                synchronized ("1") {
                    while (!hasFood) {
                        try {
                            //释放"1"内部锁，线程进入WAITING状态
                            "1".wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    hasFood = false;
                    System.out.println("食物吃完了" + i);
                    "1".notifyAll();
                }
            }
        });
        thread2.start();

        //子线程不退出，主线程不能提前退出
        while (thread1.isAlive() || thread2.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("主线程退出");

    }


}
