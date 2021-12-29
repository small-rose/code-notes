package cn.com.lgs.inner_classes;

/**
 * 内部类访问外部类对象引用
 *
 * @author luguosong
 * @date 2021/12/29 11:28
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }

    public class Inner {
        public DotThis outer() {
            //返回外部类对象引用
            return DotThis.this;
            // 如果直接写“this”，引用的会是Inner的“this”
        }
    }

    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dt = new DotThis();
        DotThis.Inner dti = dt.inner();
        dti.outer().f();
    }
}
