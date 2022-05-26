package com.luguosong._04_structural._02_bridge_pattern;

/**
 * 测试类
 *
 * @author 10545
 * @date 2022/5/8 23:03
 */
public class Demo {
    public static void main(String[] args) {
        Image image;
        ImageImp imp;
        image = (Image) XMLUtil.getBean("image");
        imp = (ImageImp) XMLUtil.getBean("os");
        //依赖注入
        image.setImageImp(imp);

        image.parseFile("小龙女");
    }
}
