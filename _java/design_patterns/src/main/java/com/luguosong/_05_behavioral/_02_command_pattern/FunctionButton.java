package com.luguosong._05_behavioral._02_command_pattern;

/**
 * 功能按键类，充当请求调用者
 * @author luguosong
 * @date 2022/6/7 10:43
 */
public class FunctionButton {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void click(){
        System.out.print("单击功能键：");
        command.execute();
    }
}
