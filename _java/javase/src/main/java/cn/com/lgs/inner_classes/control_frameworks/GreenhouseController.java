package cn.com.lgs.inner_classes.control_frameworks;

/**
 * 配置和执行温室系统
 *
 * @author luguosong
 * @date 2022/1/25 10:07
 */
public class GreenhouseController {
    public static void main(String[] args) {
        GreenhouseControls gc = new GreenhouseControls();
        // 也可以从文本文件中解析配置信息，
        // 而不是使用代码：
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        gc.addEvent(
                new GreenhouseControls.Terminate(5000));
        gc.run();
    }
}
