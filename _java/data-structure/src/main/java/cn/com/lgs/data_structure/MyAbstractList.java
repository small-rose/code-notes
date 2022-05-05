package cn.com.lgs.data_structure;

/**
 * 将链表和动态数组中的公共代码抽取出来
 *
 * @author 10545
 * @date 2022/4/30 19:08
 */
public abstract class MyAbstractList<AnyType> implements MyList<AnyType> {
    //元素个数
    protected int theSize;

    /**
     * 向末尾添加元素
     *
     * @param anyType
     * @return
     */
    @Override
    public boolean add(AnyType anyType) {
        //调用重载方法
        add(size(), anyType);
        return true;
    }

    /**
     * 获取元素的个数
     *
     * @return
     */
    @Override
    public int size() {
        return theSize;
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return theSize == 0;
    }

    /**
     * 判断是否包含元素o
     *
     * @param o
     * @return
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * index范围检查
     *
     * @param index
     */
    protected void rangeCheck(int index) {
        if (index >= theSize || index < 0)
            throw new IndexOutOfBoundsException("index超出范围");
    }

    /**
     * 因为添加元素时，元素index允许与元素个数theSize相同。因此独立检验其index
     *
     * @param index
     */
    protected void rangeCheckForAdd(int index) {
        if (index > theSize || index < 0)
            throw new IndexOutOfBoundsException("index超出范围");
    }
}
