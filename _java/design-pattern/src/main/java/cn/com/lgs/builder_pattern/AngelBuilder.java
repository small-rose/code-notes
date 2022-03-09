package cn.com.lgs.builder_pattern;

/**
 * @author 10545
 * @date 2022/3/9 23:01
 */
public class AngelBuilder extends ActorBuilder{
    @Override
    public void buildType() {
        actor.setType("天使");
    }

    @Override
    public void buildSex() {
        actor.setSex("");
    }

    @Override
    public void buildFace() {

    }

    @Override
    public void buildCostume() {

    }

    @Override
    public void buildHairstyle() {

    }
}
