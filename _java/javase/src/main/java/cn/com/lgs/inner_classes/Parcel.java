package cn.com.lgs.inner_classes;

/**
 * 外部类的创建
 *
 * @author luguosong
 * @date 2021/12/29 9:59
 */
public class Parcel {

    class Destination {
        private String label;

        Destination(String whereTo) {
            label = whereTo;
        }
    }

    public Destination to(String s) {
        return new Destination(s);
    }

    public static void main(String[] args) {
        //创建内部类对象方式一：先创建外部类对象，再创建内部类对象
        Destination hello = new Parcel().new Destination("hello");
        //创建内部类对象方式二：将创建内部类的行为包装到外部类的方法中
        Destination hello1 = new Parcel().to("hello");
    }
}
