package cn.com.lgs.bridge_pattern;

/**
 * JPG格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/4 23:50
 */
public class JPGImage extends Image{
    @Override
    public void parseFile(String fileName) {
        //模拟解析JPG文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName+",格式为JPG");
    }
}
