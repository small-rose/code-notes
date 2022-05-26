package com.luguosong._04_structural._02_bridge_pattern;

/**
 * 抽象操作系统实现类，充当实现类接口
 *
 * @author 10545
 * @date 2022/5/4 23:11
 */
public interface ImageImp {
    //显示图像矩阵m
    public void doPaint(Matrix m);
}
