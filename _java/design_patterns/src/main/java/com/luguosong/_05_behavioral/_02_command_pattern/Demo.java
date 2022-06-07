package com.luguosong._05_behavioral._02_command_pattern;

import com.luguosong.util.XMLUtil;

/**
 * @author luguosong
 * @date 2022/6/7 17:36
 */
public class Demo {
    public static void main(String[] args) {
        FunctionButton fb = new FunctionButton();
        fb.setCommand((Command) XMLUtil.getBean("_java/design_patterns/src/main/java/com/luguosong/_05_behavioral/_02_command_pattern/config.xml").get(0));
        fb.click();
    }
}
