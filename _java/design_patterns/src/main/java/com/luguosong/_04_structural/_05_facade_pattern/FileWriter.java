package com.luguosong._04_structural._05_facade_pattern;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件保存类，充当子系统类
 *
 * @author 10545
 * @date 2022/5/16 22:11
 */
public class FileWriter {
    public void write(String encryptStr,String fileNameDes){
        System.out.print("保存密文，写入文件。");
        try {
            FileOutputStream outFs = new FileOutputStream(fileNameDes);
            outFs.write(encryptStr.getBytes());
            outFs.close();
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在！");
        } catch (IOException e) {
            System.out.println("文件操作错误！");
        }
    }
}
