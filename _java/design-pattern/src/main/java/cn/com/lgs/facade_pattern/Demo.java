package cn.com.lgs.facade_pattern;

/**
 * 客户端测试类
 *
 * @author 10545
 * @date 2022/5/16 22:34
 */
public class Demo {
    public static void main(String[] args) {
        EncryptFacade ef = new EncryptFacade();
        ef.fileEncrypt("_java/design-pattern/src/main/java/cn/com/lgs/facade_pattern/src.txt","_java/design-pattern/src/main/java/cn/com/lgs/facade_pattern/des.txt");
    }
}
