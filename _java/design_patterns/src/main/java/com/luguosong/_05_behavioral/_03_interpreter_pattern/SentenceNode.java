package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * 单句子节点类，充当非终结符表达式角色
 * @author luguosong
 * @date 2022/6/17
 */
public class SentenceNode extends AbstractNode{

    private AbstractNode direction;
    private AbstractNode action;
    private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return direction.interpret()+action.interpret()+distance.interpret();
    }
}
