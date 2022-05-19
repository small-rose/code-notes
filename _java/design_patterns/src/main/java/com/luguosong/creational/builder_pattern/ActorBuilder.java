package com.luguosong.creational.builder_pattern;

/**
 * 游戏角色生成器，充当抽象生成器
 *
 * @author 10545
 * @date 2022/3/9 22:22
 */
public abstract class ActorBuilder {
    protected Actor actor = new Actor();

    public abstract void buildType();

    public abstract void buildSex();

    public abstract void buildFace();

    public abstract void buildCostume();

    public abstract void buildHairstyle();

    public Actor createActor() {
        return actor;
    }
}
