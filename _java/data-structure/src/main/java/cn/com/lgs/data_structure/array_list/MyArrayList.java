package cn.com.lgs.data_structure.array_list;

/**
 * 数组的简单实现
 *
 * @author luguosong
 * @date 2021/12/11 9:56
 */
public class MyArrayList<AnyType> {
    /**
     * 数组默认
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 内部实际元素个数
     */
    private int theSize;

    /**
     * 存放元素的空间
     */
    private AnyType[] theItems;


    /**
     * 默认构造
     */
    public MyArrayList() {
        theSize = 0;
        theItems = (AnyType[]) new Object[DEFAULT_CAPACITY];
    }


    /**
     * 动态扩容
     *
     * 当minCapacity大于当前数组容量，进行扩容
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {

    }
}
