package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * 动作节点类，充当终结符表达式角色
 * @author luguosong
 * @date 2022/6/17
 */
public class ActionNode extends AbstractNode{
    private String action;

    public ActionNode(String action) {
        this.action = action;
    }

    @Override
    public String interpret() {
        switch (action){
            case "move":
                return "移动";
            case "run":
                return "快速移动";
            default:
                return "无效命令";
        }
    }
}
