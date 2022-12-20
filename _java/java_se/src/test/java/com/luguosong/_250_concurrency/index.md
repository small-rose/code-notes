
# 基本概念

{: .new-title}
> 进程
>
> 在操作系统中运行的一个应用程序
>
> 每个进程之间是独立的，每个进程均运行在其专用且受保护的内存空间内

{: .new-title}
> 线程
>
> 一个`进程`想要执行任务，必须得有`线程`
>
> 每个`进程`至少有一个`线程`
>
> 一个`进程`的所有任务都在`线程`中执行

{: .new-title}
> 线程的串行
>
> 一个线程中执行多个任务，那么只能一个一个地按顺序执行这些任务
>
> 在同一时间内，一个线程只能执行一个任务

# 多线程

{: .new-title}
> 多线程
>
> 一个进程可以开启多个线程
>
> 所有线程可以并行执行不同的任务，提高执行效率

- 优点
    - 能适当提高程序的执行效率
    - 能适当提高CPU、内存利用率
- 缺点
    - 开启线程需要占用一定的内存空间
    - 线程越多，CPU在调度线程上的开销就越大
    - 程序设计更加复杂

{: .new-title}
> 默认线程
>
> 每一个Java程序启动后，会默认开启一个线程，称为主线程（main方法所在的线程）

# 开启线程

```java
class Demo {
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
}
```

```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("线程：" + Thread.currentThread().getName());
    }
}

class Demo {
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
}
```

{: .warning}
> 虽然`start()`内部调用的是`run()`方法
>
> 需要调用`start()`方法才能开启多线程，而不是直接调用`run()`方法


{: .note-title}
> 多线程内存布局
>
> 每个线程都有自己的`PC寄存器`，`虚拟机栈`,`本地方法栈`
>
> 多个线程共享`堆`,`方法区`

# 线程的状态

- `NEW`：新建
- `RUNNABLE`：可运行状态
- `BLOCKED`：阻塞状态，等待内部锁
- `WATTING`：等待状态
- `TIMED_WAITTING`：定时等待状态
- `TERMINATED`：终止状态

# 线程常用方法

- `sleep()`:线程休眠
- `interrupt()`:终止线程
- `join()`:等指定线程执行完后再执行当前任务
- `isAlive()`:判断线程是否还活着

# 线程安全问题

## 问题描述

多个线程共享同一个资源，至少一个线程在执行写操作，则会出现线程安全问题。

**不考虑线程安全问题进行卖票：**

```java
class Demo {
    static int tickets = 100;

    /**
     * 不考虑线程安全问题进行卖票
     */
    @Test
    public void testThreadSafe() {
        for (int i = 1; i <= 4; i++) {
            Thread thread = new Thread(() -> {
                String name = Thread.currentThread().getName();
                while (tickets > 0) {
                    System.out.println(name + "卖出一张票,还剩：" + tickets--);
                }
            });
            thread.setName("线程" + i);
            thread.start();
        }
    }
}
```

可以看到，结果存在问题

```text
线程2卖出一张票,还剩：100
线程2卖出一张票,还剩：98
线程2卖出一张票,还剩：97
线程2卖出一张票,还剩：96
线程2卖出一张票,还剩：95
线程2卖出一张票,还剩：94
线程2卖出一张票,还剩：93
线程4卖出一张票,还剩：92
线程3卖出一张票,还剩：99
.
省略...
.
线程3卖出一张票,还剩：5
线程3卖出一张票,还剩：4
线程3卖出一张票,还剩：3
线程3卖出一张票,还剩：2
线程3卖出一张票,还剩：1
线程4卖出一张票,还剩：59
线程2卖出一张票,还剩：69
线程1卖出一张票,还剩：22
```

## 线程同步-同步语句

{: .new-title}
> synchronized(obj)原理
>
> 每个对象都有一个与它相关的内部锁
>
> 第一个执行到同步语句的线程可以获得`obj`的内部锁,在执行完同步语句中的代码后释放此锁
>
> 只要一个线程持有了内部锁，那么其它线程在同一时间将无法获得此锁。当他们试图获取此锁时，会进入`BLOCKED`状态。

{: .warning}
> 要保证线程使用的是`同一个对象`的内部锁

```java
class Demo {
    static int tickets = 100;

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
}
```

## 线程同步-同步方法

用`synchronized`修饰成员方法，不包括构造方法和静态方法

同步方法会锁住整个方法，没有同步语句灵活精确

```java
class Demo {
    static int tickets = 100;

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
}
```

## 线程安全经典案例

- 单例懒汉模式
- `ArrayList`中的方法没有用synchronized修饰，线程不安全。`Vector`中的代码使用synchronized修饰，线程安全。
- `StringBuilder`非线程安全，`StringBuffer`线程安全
- `HashMap`非线程安全，`Hashtable`线程安全

# 死锁

{: .new-title}
> 死锁
>
> 两个或多个线程永远阻塞，相互等待

```java
class Demo {
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
}
```

# 线程间通信

- `wait()`:导致当前线程等待，直到另一个线程为此对象调用`notify()`方法或`notifyAll()`方法。`释放内部锁`
  ，当前线程进入`WAITING`或`TIMED_WAITING`状态
- `notify()`:唤醒在此对象的监视器上等待的单个线程。如果有任何线程正在等待该对象，则选择唤醒其中一个线程。选择是任意的，由实现自行决定。
- `notifyAll()`:唤醒在此对象的监视器上等待的所有线程。

{: .warning}
> 只有当线程获取线程锁时，才能通过锁对象调用这些方法

```java
class Demo {
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
```

# 可重入锁（ReentrantLock）

{: .note-title}
> 可重入
>
> 同一线程可以重复获取同一个锁
>
> 其实`synchronized`也是可重入的

- ReentrantLock.lock
    - 如果此锁没有被另一个线程持有，则将锁的持有计数设为 1，并且此方法立即返回
    - 如果当前线程已经持有此锁，则将锁的持有计数加 1，并且此方法立即返回
    - 如果此锁被另一个线程持有，并且在获得锁之前，此线程将一直处于休眠状态，此时锁的持有计数被设为 1
- ReentrantLock.tryLock
    - 如果此锁没有被另一个线程持有，则将锁的持有计数设为 1，并且此方法立即返回 true
    - 如果当前线程已经持有此锁，则将锁的持有计数加 1，并且此方法立即返回 true。
    - 如果锁被另一个线程持有，则此方法立即返回 false
- ReentrantLock.unlock
    - 如果当前线程持有此锁，则将持有计数减 1
    - 如果持有计数现在为 0，则释放此锁
    - 如果当前线程没有持有此锁，则抛出 java.lang.IllegalMonitorStateException
- ReentrantLock.isLocked
    - 查看此锁是否被任意线程持有

# 线程池（Thread Pool）

{: .note-title}
> 普通线程
>
> 执行完一个任务后，生命周期就结束了

{: .note-title}
> 工作线程
>
> 任务没来一直等，任务来了干活

{: .new-title}
> 线程池
>
> 线程池由`工作线程`组成，可以最大程度减少线程创建、销毁带来的开销。

```java
class Demo {
    /**
     * 线程池示例
     */
    @Test
    public void testThreadPool() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println(Thread.currentThread());
        });
        pool.shutdown();
    }
}
```
