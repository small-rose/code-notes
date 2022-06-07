package com.luguosong._03_creational._04_builder_pattern;

import com.luguosong.util.XMLUtil;

/**
 * 客户端
 *
 * @author luguosong
 * @date 2022/3/15 16:39
 */
public class Demo {
    public static void main(String[] args) {
        //通过xml创建具体生成器对象
        ActorBuilder ab;
        ab=(ActorBuilder) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_03_creational/_04_builder_pattern/config.xml").get(0);

        //创建指挥官，并通过指挥官创建对象的各个部件，最后返回对象
        ActorController ac = new ActorController();
        Actor actor;
        actor = ac.construct(ab);

        //打印对象
        System.out.println(actor);
    }
}
