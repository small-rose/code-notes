package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * 方向节点类，充当终结符表达式角色
 * @author luguosong
 * @date 2022/6/17
 */
public class DirectionNode extends AbstractNode{

    private String direction;

    public DirectionNode(String direction) {
        this.direction = direction;
    }

    @Override
    public String interpret() {
        switch (direction){
            case "up":
                return "向上";
            case "down":
                return "向下";
            case "left":
                return "向左";
            case "right":
                return "向右";
            default:
                return "无效指令";
        }
    }
}
