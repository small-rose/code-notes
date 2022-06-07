package com.luguosong._04_structural._02_bridge_pattern;

import com.luguosong.util.XMLUtil;

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
        image = (Image) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_02_bridge_pattern/config.xml").get(0);
        imp = (ImageImp) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_04_structural/_02_bridge_pattern/config.xml").get(1);
        //依赖注入
        image.setImageImp(imp);

        image.parseFile("小龙女");
    }
}
