package cn.com.lgs.abstract_factory_pattern;

/**
 * 界面皮肤工厂接口，充当抽象工厂接口
 *
 * @author 10545
 * @date 2022/2/23 21:27
 */
public interface SkinFactory {
    public Button createButton();
    public TextField createTextField();
    public ComboBox createComboBox();
}
