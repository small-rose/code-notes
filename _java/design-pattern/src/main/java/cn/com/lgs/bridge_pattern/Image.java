package cn.com.lgs.bridge_pattern;

/**
 * 抽象图像类，充当抽象类
 *
 * @author 10545
 * @date 2022/5/4 23:33
 */
public abstract class Image {
    protected ImageImp imp;

    //注入实现类接口对象
    public void setImageImp(ImageImp imp) {
        this.imp = imp;
    }

    public abstract void parseFile(String fileName);
}
