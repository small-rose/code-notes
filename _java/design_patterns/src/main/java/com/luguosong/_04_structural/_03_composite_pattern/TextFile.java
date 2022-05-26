package com.luguosong._04_structural._03_composite_pattern;

/**
 * 文本文件类，充当叶子构件类
 *
 * @author luguosong
 * @date 2022/5/10 17:02
 */
public class TextFile extends AbstractFile {

    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    @Override
    public void add(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public void remove(AbstractFile file) {
        System.out.println("对不起，不支持该方法！");
    }

    @Override
    public AbstractFile getChild(int i) {
        System.out.println("对不起，不支持该方法！");
        return null;
    }

    @Override
    public void killVirus() {
        //模拟杀毒
        System.out.println("---对文本文件'" + name + "'进行杀毒");
    }
}
