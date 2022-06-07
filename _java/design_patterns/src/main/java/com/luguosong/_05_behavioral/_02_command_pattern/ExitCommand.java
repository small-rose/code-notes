package com.luguosong._05_behavioral._02_command_pattern;

/**
 * 退出命令类，充当具体命令类
 * @author luguosong
 * @date 2022/6/7 11:20
 */
public class ExitCommand extends Command{
    private SystemExitClass seObj; //维持对请求接收者的引用

    public ExitCommand() {
        seObj=new SystemExitClass();
    }

    /**
     * 命令执行方法，将调用请求接收者的业务方法
     */
    @Override
    public void execute() {
        seObj.exit();
    }
}
