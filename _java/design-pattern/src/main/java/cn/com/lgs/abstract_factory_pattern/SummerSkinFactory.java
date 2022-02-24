package cn.com.lgs.abstract_factory_pattern;

/**
 * Summer皮肤工厂，充当具体工厂
 *
 * @author 10545
 * @date 2022/2/23 21:53
 */
public class SummerSkinFactory implements SkinFactory{
    @Override
    public Button createButton() {
        return new SummerButton();
    }

    @Override
    public TextField createTextField() {
        return new SummerTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}
