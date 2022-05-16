package cn.com.lgs.decorator_pattern;

/**
 * 列表框类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:22
 */
public class ListBox extends Component{
    @Override
    public void display() {
        System.out.println("显示列表框");
    }
}
