package cn.com.lgs.inner_classes.control_frameworks;

import java.time.*;

/**
 * 用于任何控制事件的公共方法
 *
 * @author luguosong
 * @date 2022/1/25 9:43
 */
public abstract class Event {
    private Instant eventTime;
    protected final Duration delayTime;

    public Event(long millisecondDelay) {
        //获取你希望Event运行的时间（从对象创建时开始计时，以毫秒计算）
        delayTime = Duration.ofMillis(millisecondDelay);
        start();
    }


    /**
     * 取得当前时间，再加上延迟时间，从而生成事件将要发生的时间。
     */
    public void start() { // 可以重启
        eventTime = Instant.now().plus(delayTime);
    }

    /**
     * 判断是否可以运行action方法
     */
    public boolean ready() {
        return Instant.now().isAfter(eventTime);
    }


    /**
     * 触发事件
     */
    public abstract void action();
}
