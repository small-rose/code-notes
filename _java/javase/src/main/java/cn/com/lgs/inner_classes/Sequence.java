package cn.com.lgs.inner_classes;

/**
 * 内部类访问外部类字段
 *
 * @author luguosong
 * @date 2021/12/29 10:17
 */

interface Selector {
    boolean end();

    Object current();

    void next();
}

public class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    //内部类
    private class SequenceSelector implements Selector {
        private int i = 0;

        @Override
        public boolean end() {
            //内部类访问外部items数组
            return i == items.length;
        }

        @Override
        public Object current() {
            //内部类访问外部items数组
            return items[i];
        }

        @Override
        public void next() {
            //内部类访问外部items数组
            if (i < items.length) i++;
        }
    }

    //外部类通过方法创建内部类对象
    public Selector selector() {
        return new SequenceSelector();
    }

    public static void main(String[] args) {
        //创建外部类添加数组
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        //创建内部类
        Selector selector = sequence.selector();
        //内部内访问外部类数组进行遍历
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
