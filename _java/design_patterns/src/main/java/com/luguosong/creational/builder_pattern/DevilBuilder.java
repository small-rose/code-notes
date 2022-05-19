package com.luguosong.creational.builder_pattern;

/**
 * 恶魔角色生成器，充当具体生成器
 *
 * @author luguosong
 * @date 2022/3/15 10:43
 */
public class DevilBuilder extends ActorBuilder{
    @Override
    public void buildType() {
        actor.setType("恶魔");
    }

    @Override
    public void buildSex() {
        actor.setSex("妖");
    }

    @Override
    public void buildFace() {
        actor.setFace("丑陋");
    }

    @Override
    public void buildCostume() {
        actor.setCostume("黑衣");
    }

    @Override
    public void buildHairstyle() {
        actor.setHairstyle("光头");
    }
}
