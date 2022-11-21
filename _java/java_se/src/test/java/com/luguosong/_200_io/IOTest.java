package com.luguosong._200_io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.function.Consumer;

/**
 * IO练习
 *
 * @author luguosong
 * @date 2022/11/16
 */
public class IOTest {
    /**
     * 遍历文件夹下的所有文件（包括子文件夹下的文件）
     * 知识点：对File类的使用
     *
     * @param dir
     * @param operation
     */
    private void search(File dir, Consumer<File> operation) {
        //入参为空直接返回
        if (dir == null || operation == null) return;
        //文件夹为空或不是文件夹直接返回
        if (!dir.exists() || dir.isFile()) return;
        File[] files = dir.listFiles();
        for (File file : files) {
            operation.accept(file);
            if (file.isDirectory())
                search(file, operation);
        }
    }

    /**
     *
     */
    @Test
    public void testSearch() {
        File folder = new File("src/main/resources/test1");
        search(folder, (file) -> {
            System.out.println(file.getAbsolutePath());
        });
    }


}
