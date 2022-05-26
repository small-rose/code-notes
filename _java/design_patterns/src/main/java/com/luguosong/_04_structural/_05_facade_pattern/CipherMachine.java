package com.luguosong._04_structural._05_facade_pattern;

/**
 * 数据加密类，充当子系统类
 *
 * @author 10545
 * @date 2022/5/16 21:33
 */
public class CipherMachine {
    public String encrypt(String plainText) {
        System.out.print("数据加密，将明文转换为密文：");
        String es = "";
        for (int i = 0; i < plainText.length(); i++) {
            String c = String.valueOf(plainText.charAt(i) % 7);
            es += c;
        }
        System.out.println(es);
        return es;
    }
}
