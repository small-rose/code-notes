package com.luguosong._04_structural._06_flyweight_pattern;

/**
 * 白色棋子类，充当具体享元类
 *
 * @author 10545
 * @date 2022/5/17 21:49
 */
public class WhiteIgoChessman extends IgoChessman{
    @Override
    public String getColor() {
        return "白色";
    }
}
