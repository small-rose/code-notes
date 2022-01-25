package cn.com.lgs.inner_classes.control_frameworks;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于控制系统的可复用框架
 *
 * @author luguosong
 * @date 2022/1/25 9:52
 */
public class Controller {
    // 用java.util中的一个类来保存Event对象：
    private List<Event> eventList = new ArrayList<>();

    public void addEvent(Event c) {
        eventList.add(c);
    }

    public void run() {
        while (eventList.size() > 0)
            // 创建一个副本，这样在选择列表
            // 中的元素时就不会改动列表了：
            for (Event e : new ArrayList<>(eventList))
                if (e.ready()) {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
    }
}
