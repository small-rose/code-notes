package cn.com.lgs.flyweight_pattern;

/**
 * 围棋棋子类，充当抽象享元类
 *
 * @author 10545
 * @date 2022/5/17 21:30
 */
public abstract class IgoChessman {
    public abstract String getColor();

    public void display(){
        System.out.println("棋子的颜色："+this.getColor());
    }
}
