package com.luguosong._04_structural._04_decorator_pattern;

/**
 * 构件装饰类，充当抽象装饰类
 *
 * @author luguosong
 * @date 2022/5/15 16:24
 */
public class ComponentDecorator extends Component {

    /**
     * 维持对抽象构件类型对象的引用
     */
    private Component component;

    //注入抽象构件类型的对象
    public ComponentDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void display() {
        component.display();
    }
}
