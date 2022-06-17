package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * @author luguosong
 * @date 2022/6/17
 */
public class DistanceNode extends AbstractNode{
    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    @Override
    public String interpret() {
        return this.distance;
    }
}
