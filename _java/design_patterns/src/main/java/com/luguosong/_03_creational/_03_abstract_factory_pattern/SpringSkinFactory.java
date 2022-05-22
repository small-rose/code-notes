package com.luguosong._03_creational._03_abstract_factory_pattern;

/**
 * Spring皮肤工厂，充当具体工厂
 *
 * @author 10545
 * @date 2022/2/23 21:35
 */
public class SpringSkinFactory implements SkinFactory{
    @Override
    public Button createButton() {
        return new SpringButton();
    }

    @Override
    public TextField createTextField() {
        return new SpringTextField();
    }

    @Override
    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}
