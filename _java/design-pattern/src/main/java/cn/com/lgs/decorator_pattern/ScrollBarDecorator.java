package cn.com.lgs.decorator_pattern;

/**
 * 滚动条装饰类，充当具体装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:28
 */
public class ScrollBarDecorator extends ComponentDecorator{

    public ScrollBarDecorator(Component component) {
        super(component);
    }

    /**
     * 重写抽象装饰类方法
     */
    @Override
    public void display() {
        this.setScrollBar();
        super.display();
    }

    /**
     * 装饰方法
     */
    public void setScrollBar(){
        System.out.println("为构件增加滚动条");
    }
}
