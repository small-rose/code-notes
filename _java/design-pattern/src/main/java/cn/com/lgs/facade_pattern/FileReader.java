package cn.com.lgs.facade_pattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件读取类，充当子系统
 *
 * @author luguosong
 * @date 2022/5/15 19:32
 */
public class FileReader {
    public String read(String fileNameSrc){
        System.out.print("读取文件，获取明文：");
        StringBuffer sb = new StringBuffer();
        try {
            FileInputStream inFs = new FileInputStream(fileNameSrc);
            int data;
            while((data=inFs.read())!=-1){
                sb=sb.append((char)data);
            }
            inFs.close();
            System.out.println(sb.toString());
        } catch (FileNotFoundException e) {
            System.out.println("文件不存在");
        } catch (IOException e) {
            System.out.println("文件操作错误");
        }
        return sb.toString();
    }
}
