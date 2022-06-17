package com.luguosong._05_behavioral._03_interpreter_pattern;

import java.util.Stack;

/**
 * 指令处理类
 *
 * @author luguosong
 * @date 2022/6/17
 */
public class InstructionHandler {
    private AbstractNode node;

    public void handle(String instruction) {
        AbstractNode left = null, right = null;
        AbstractNode direction = null, action = null, distance = null;
        //声明一个栈对象，用于存储抽象语法树
        Stack<AbstractNode> stack = new Stack<>();
        String[] words = instruction.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equalsIgnoreCase("and")) {
                left = (AbstractNode) stack.pop();
                String word1 = words[++i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                right=new SentenceNode(direction,action,distance);
                stack.push(new AndNode(left,right));
            }else{
                String word1=words[i];
                direction = new DirectionNode(word1);
                String word2=words[++i];
                action=new ActionNode(word2);
                String word3=words[++i];
                distance=new DistanceNode(word3);
                left=new SentenceNode(direction,action,distance);
                stack.push(left);
            }
        }
        this.node=(AbstractNode) stack.pop();
    }

    public String output(){
        String result = node.interpret(); //解释表达式
        return result;
    }
}
