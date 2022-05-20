package com.luguosong._03_creational.builder_pattern;

/**
 * 角色控制器，充当指挥官
 *
 * @author luguosong
 * @date 2022/3/15 11:02
 */
public class ActorController {
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor = ab.createActor();
        return actor;
    }
}
