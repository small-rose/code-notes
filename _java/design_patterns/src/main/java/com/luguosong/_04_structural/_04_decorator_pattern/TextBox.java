package com.luguosong._04_structural._04_decorator_pattern;

/**
 * 文本框类，充当具体构件类
 *
 * @author luguosong
 * @date 2022/5/15 16:20
 */
public class TextBox extends Component{
    @Override
    public void display() {
        System.out.println("显示文本框");
    }
}
