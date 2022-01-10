package cn.com.lgs.inner_classes;

/**
 * 局部类示例：验证两个号码的合法性
 *
 * @author 10545
 * @date 2022/1/3 20:37
 */
public class LocalClassExample {
    static String regularExpression = "[^0-9]";

    public static void validatePhoneNumber(String phoneNumber1, String phoneNumber2) {
        //JDK8之前，局部类引用的外部变量需要使用final修饰
        //final int numberLength = 10;
        //JDK8以及之后版本不需要final修饰
        int numberLength = 10;

        /////////////////////////////////////////////////////////////////////////////////

        /**
         * 局部类
         */
        class PhoneNumber {

            //局部类中不可以有静态成员
            //static String phone;
            //但允许常量
            final static String PHONE = "123-456-7890";

            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber) {
                String currentNumber = phoneNumber.replaceAll(regularExpression, "");
                //局部类可以访问所属块的局部变量numberLength，该变量必须是effectively final的
                if (currentNumber.length() == numberLength) {
                    formattedPhoneNumber = currentNumber;
                } else {
                    formattedPhoneNumber = null;
                }
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }

            //JDK8之后可以访问所在方法的参数，但同样是effectively final的
            public void printOriginalNumbers() {
                System.out.println("原始数字是：" + phoneNumber1 + "和" + phoneNumber2);
            }
        }

        /////////////////////////////////////////////////////////////////////////////////

        PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        //JDK8之后可以访问局部类外部非final修饰的
        myNumber1.printOriginalNumbers();

        if (myNumber1.getNumber() == null) System.out.println("第一个数字无效");
        else System.out.println("第一个数字是：" + myNumber1.getNumber());

        if (myNumber2.getNumber() == null) System.out.println("第二个数无效");
        else System.out.println("第二个数字是：" + myNumber2.getNumber());
    }

    public static void main(String[] args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
