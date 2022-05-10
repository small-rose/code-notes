package cn.com.lgs.composite_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹类，充当容器构件类
 *
 * @author luguosong
 * @date 2022/5/10 17:29
 */
public class Folder extends AbstractFile {

    //定于集合fileList,用于存储AbstractFile类型的成员
    private List<AbstractFile> fileList = new ArrayList<AbstractFile>();

    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        fileList.add(file);
    }

    @Override
    public void remove(AbstractFile file) {
        fileList.remove(file);
    }

    @Override
    public AbstractFile getChild(int i) {
        return fileList.get(i);
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("***对文件夹'" + name + "'进行杀毒");

        //递归调用成员构件的killVirus()方法
        for (AbstractFile abstractFile : fileList) {
            abstractFile.killVirus();
        }
    }
}
