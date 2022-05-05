package cn.com.lgs.adapter_pattern;

/**
 * 警车适配器，充当适配器
 *
 * @author 10545
 * @date 2022/5/2 20:49
 */
public class PoliceCarAdapter extends CarController {
    private PoliceSound sound;
    private PoliceLamp lamp;

    /**
     * 适配器构造
     */
    public PoliceCarAdapter() {
        sound = new PoliceSound();
        lamp = new PoliceLamp();
    }


    @Override
    public void phonate() {
        //调用适配者类PoliceSound的方法
        sound.alarmSound();
    }

    @Override
    public void twinkle() {
        //调用适配者类PoliceLamp的方法
        lamp.alarmLamp();
    }
}
