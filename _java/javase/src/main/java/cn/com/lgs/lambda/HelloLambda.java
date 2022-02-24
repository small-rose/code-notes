package cn.com.lgs.lambda;

/**
 * Lambda HelloWorld
 *
 * @author 10545
 * @date 2022/2/24 21:40
 */
interface Command {
    void process(int element);
}

class ProcessArray {
    public void process(int[] target, Command cmd) {
        for (int t : target) {
            cmd.process(t);
        }
    }
}

/**
 * 使用匿名内部类创建Command对象
 */
class CommandTest1 {
    public static void main(String[] args) {
        ProcessArray processArray = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        processArray.process(target, new Command() {
            @Override
            public void process(int element) {
                System.out.println("数组元素的平方是：" + element * element);
            }
        });
    }
}

/**
 * 使用Lambda创建Command对象
 */
class CommandTest2 {
    public static void main(String[] args) {
        ProcessArray processArray = new ProcessArray();
        int[] target = {3, -4, 6, 4};
        processArray.process(target, (int element) -> {
            System.out.println("数组元素的平方是：" + element * element);
        });
    }
}
