package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * And节点类，充当非终结符表达式角色
 * @author luguosong
 * @date 2022/6/17
 */
public class AndNode extends AbstractNode{

    private AbstractNode left;
    private AbstractNode right;

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String interpret() {
        return left.interpret() + "再" +right.interpret();
    }
}
