package com.luguosong.creational.builder_pattern;

/**
 * 英雄角色生成器，充当具体生成器
 *
 * @author 10545
 * @date 2022/3/9 22:50
 */
public class HeroBuilder extends ActorBuilder{

    @Override
    public void buildType() {
        actor.setType("英雄");
    }

    @Override
    public void buildSex() {
        actor.setSex("男");
    }

    @Override
    public void buildFace() {
        actor.setFace("英俊");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("盔甲");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("飘逸");
    }
}
