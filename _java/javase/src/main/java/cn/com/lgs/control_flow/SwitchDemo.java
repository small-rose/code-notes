package cn.com.lgs.control_flow;

/**
 * @author luguosong
 * @date 2021/12/18 14:54
 */
public class SwitchDemo {
    public static void main(String[] args) {
        //选择器
        switch ("1" + "2") {
            //整数值
            case"3":
                System.out.println(3);
                break;
            case "12":
                System.out.println(12);
                break;
            //默认语句
            default:
                System.out.println("默认语句");
        }
    }
}
