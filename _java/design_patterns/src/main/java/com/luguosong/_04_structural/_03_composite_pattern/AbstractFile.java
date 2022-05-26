package com.luguosong._04_structural._03_composite_pattern;

/**
 * 抽象文件类，充当抽象构件类
 *
 * @author luguosong
 * @date 2022/5/10 16:53
 */
public abstract class AbstractFile {
    public abstract void add(AbstractFile file);

    public abstract void remove(AbstractFile file);

    public abstract AbstractFile getChild(int i);

    public abstract void killVirus();
}
