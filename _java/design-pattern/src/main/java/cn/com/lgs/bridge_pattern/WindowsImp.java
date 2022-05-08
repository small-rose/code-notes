package cn.com.lgs.bridge_pattern;

/**
 * Windows操作系统实现类，充当具体实现类
 *
 * @author 10545
 * @date 2022/5/4 23:13
 */
public class WindowsImp implements ImageImp{
    @Override
    public void doPaint(Matrix m) {
        //调用windows系统的绘制函数绘制像素矩阵
        System.out.print("在windows系统中显示图像:");
    }
}
