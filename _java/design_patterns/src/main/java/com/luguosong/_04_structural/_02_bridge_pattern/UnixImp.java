package com.luguosong._04_structural._02_bridge_pattern;

/**
 * UNIX操作系统实现类，充当具体实现类
 *
 * @author 10545
 * @date 2022/5/4 23:26
 */
public class UnixImp implements ImageImp {
    @Override
    public void doPaint(Matrix m) {
        //调用UNIX系统的绘制函数绘制像素矩阵
        System.out.print("在UNIX系统中显示图像:");
    }
}
