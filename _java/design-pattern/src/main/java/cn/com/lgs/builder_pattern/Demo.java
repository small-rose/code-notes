package cn.com.lgs.builder_pattern;

/**
 * @author luguosong
 * @date 2022/3/15 16:39
 */
public class Demo {
    public static void main(String[] args) {
        ActorBuilder ab;
        ab=(ActorBuilder) XMLUtil.getBean();

        ActorController ac = new ActorController();
        Actor actor;
        actor = ac.construct(ab);

        System.out.println(actor);
    }
}
