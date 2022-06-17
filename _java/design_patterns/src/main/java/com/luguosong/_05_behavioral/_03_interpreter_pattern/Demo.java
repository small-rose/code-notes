package com.luguosong._05_behavioral._03_interpreter_pattern;

/**
 * @author luguosong
 * @date 2022/6/17
 */
public class Demo {
    public static void main(String[] args) {
        String instruction = "down run 10 and left move 20";
        InstructionHandler handler = new InstructionHandler();
        handler.handle(instruction);

        System.out.println(handler.output());
    }
}
