package com.luguosong._05_behavioral._02_command_pattern;

/**
 * 帮助命令类，充当具体命令类
 *
 * @author luguosong
 * @date 2022/6/7 11:25
 */
public class HelpCommand extends Command {

    private DisplayHelpClass hcObj; //维持对请求接收者的引用

    public HelpCommand() {
        hcObj = new DisplayHelpClass();
    }

    /**
     * 命令执行方法，将调用请求接收者的业务方法
     */
    @Override
    public void execute() {
        hcObj.display();
    }
}
