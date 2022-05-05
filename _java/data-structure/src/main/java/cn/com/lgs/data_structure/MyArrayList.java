package cn.com.lgs.data_structure;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * 动态数组
 *
 * @author 10545
 * @date 2022/4/17 22:43
 */
public class MyArrayList<AnyType> extends MyAbstractList<AnyType> {
    //默认数组容量
    private static final int DEFAULT_CAPACITY = 10;


    //数组，用于存放元素
    private Object[] theItems;

    /**
     * 有参构造
     *
     * @param initialCapacity 数组容量
     */
    public MyArrayList(int initialCapacity) {
        //初始容量如果小于0，抛出异常
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        //初始化时容量小于DEFAULT_CAPACITY，则容量默认设置为DEFAULT_CAPACITY
        initialCapacity = (initialCapacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : initialCapacity;
        theItems = new Object[initialCapacity];
    }

    /**
     * 无参构造，内部调用有参构造
     */
    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }



    /**
     * 向指定位置添加元素
     *
     * @param index
     * @param element
     */
    @Override
    public void add(int index, AnyType element) {
        //检查index是否合规
        rangeCheckForAdd(index);

        //如果元素个数和数组容量相同（此时无法再向其中插入元素），对数组进行扩容。
        ensureCapacity(size() + 1);

        //将index位置之后的所以元素向后移一位
        for (int i = theSize; i > index; i--) {
            theItems[i] = theItems[i - 1];
        }

        //将index位置元素设置为插入值
        theItems[index] = element;

        //元素个数加一
        theSize++;
    }

    /**
     * 删除指定位置元素
     *
     * @param index
     * @return
     */
    @Override
    public AnyType remove(int index) {
        //检查index是否合规
        rangeCheck(index);

        //获取index位置的元素
        AnyType old = (AnyType) theItems[index];

        //将index之后所以元素向前移一位
        for (int i = index; i < theSize; i++) {
            theItems[i] = theItems[i + 1];
        }

        //处理最后一个元素
        theItems[--theSize] = null;

        //返回删除的元素
        return old;
    }

    /**
     * 删除第一次出现的指定元素
     *
     * @param o
     * @return
     */
    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < theSize; index++)
                if (theItems[index] == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < theSize; index++)
                if (o.equals(theItems[index])) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }


    /**
     * 清除所有元素
     */
    @Override
    public void clear() {
        for (int i = 0; i < theSize; i++) {
            theItems[i] = null;
        }
        theSize = 0;
    }

    /**
     * 设置index位置的元素,并返回原来元素
     *
     * @param index
     * @param element
     * @return
     */
    @Override
    public AnyType set(int index, AnyType element) {
        //检查index是否超出范围
        rangeCheck(index);

        AnyType old = (AnyType) theItems[index];
        theItems[index] = element;
        return old;
    }

    /**
     * 根据index获取元素
     *
     * @param index
     * @return
     */
    @Override
    public AnyType get(int index) {
        //检查index是否超出范围
        rangeCheck(index);

        return (AnyType) theItems[index];
    }

    /**
     * 查看元素第一次出现的索引
     *
     * @param o
     * @return
     */
    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < theSize; i++) {
                if (theItems[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < theSize; i++) {
                if (o.equals(theItems[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 查看元素最后一次出现的位置
     *
     * @param o
     * @return
     */
    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = theSize - 1; i >= 0; i--) {
                if (theItems[i] == null)
                    return i;
            }
        } else {
            for (int i = theSize - 1; i >= 0; i--) {
                if (o.equals(theItems[i]))
                    return i;
            }
        }
        return -1;
    }

    /**
     * 数组扩容
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity) {
        //如果新容量小于等于数组容量，则不做处理
        if (newCapacity <= theItems.length)
            return;
        Object[] old = theItems;
        //容量扩容1.5倍
        theItems = (AnyType[]) new Object[newCapacity + (newCapacity >> 1)];
        //将元素拷贝到新的数组中去
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    /**
     * 重写toString方法，用于打印
     *
     * @return
     */
    public String toString() {
        return Arrays.toString(theItems) + ",size:" + theSize;
    }
}
