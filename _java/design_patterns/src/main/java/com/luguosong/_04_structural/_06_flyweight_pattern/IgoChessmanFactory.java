package com.luguosong._04_structural._06_flyweight_pattern;

import java.util.Hashtable;

/**
 * 围棋棋子工厂类，充当享元工厂类。使用单例模式对其进行设计
 *
 * @author 10545
 * @date 2022/5/17 21:58
 */
public class IgoChessmanFactory {
    private static IgoChessmanFactory instance = new IgoChessmanFactory();
    private static Hashtable ht; //使用Hashtable来存储享元对象，充当享元池

    private IgoChessmanFactory() {
        ht = new Hashtable();
        IgoChessman black, white;
        black = new BlackIgoChessman();
        ht.put("b", black);
        white = new WhiteIgoChessman();
        ht.put("w", white);
    }

    //返回享元工厂唯一实例
    public static IgoChessmanFactory getInstance() {
        return instance;
    }

    //通过key获取存放在Hashtable中的享元对象
    public static IgoChessman getIgoChessman(String color) {
        return (IgoChessman) ht.get(color);
    }
}
