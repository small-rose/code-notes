package cn.com.lgs.engine;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * SecureRandom类示例
 *
 * @author luguosong
 * @date 2022/1/18 14:47
 */
public class SecureRandomDemo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom instance = SecureRandom.getInstance("aa");
    }
}
