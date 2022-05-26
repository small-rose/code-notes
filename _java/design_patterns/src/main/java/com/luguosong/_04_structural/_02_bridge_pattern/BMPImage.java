package com.luguosong._04_structural._02_bridge_pattern;

/**
 * BMP格式图像类，充当扩充抽象类
 *
 * @author 10545
 * @date 2022/5/7 21:25
 */
public class BMPImage extends Image {
    @Override
    public void parseFile(String fileName) {
        //模拟解析BMP文件并获得一个像素矩阵对象m
        Matrix m = new Matrix();
        imp.doPaint(m);
        System.out.println(fileName+",格式为BMP");
    }
}
