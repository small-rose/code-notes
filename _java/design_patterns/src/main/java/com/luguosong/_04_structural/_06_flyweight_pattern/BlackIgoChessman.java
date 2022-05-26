package com.luguosong._04_structural._06_flyweight_pattern;

/**
 * 黑色棋子类，充当具体享元类
 *
 * @author 10545
 * @date 2022/5/17 21:43
 */
public class BlackIgoChessman extends IgoChessman{
    @Override
    public String getColor() {
        return "黑色";
    }
}
