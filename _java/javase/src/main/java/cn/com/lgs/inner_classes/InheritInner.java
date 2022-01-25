package cn.com.lgs.inner_classes;

/**
 * 继承一个内部类
 *
 * @author luguosong
 * @date 2022/1/25 10:25
 */
class WithInner {
    class Inner {
    }
}

public class InheritInner extends WithInner.Inner {
    //- InheritInner() {} // 不能编译

    InheritInner(WithInner wi) {
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}
