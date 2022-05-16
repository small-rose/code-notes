package cn.com.lgs.decorator_pattern;

/**
 * 窗体类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:19
 */
public class Windows extends Component{
    @Override
    public void display() {
        System.out.println("显示窗体");
    }
}
