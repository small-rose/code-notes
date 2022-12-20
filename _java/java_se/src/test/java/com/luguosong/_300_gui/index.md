---
layout: default
title: 图形化编程
nav_order: 300
parent: JavaSE
---

# 实现方案

- `AWT`：最早的GUI编程开发包，界面风格跟随系统
- `SWT`：IBM推出。Eclipse使用SWT开发
- `Swing`：在AWT基础上扩充功能，不同操作系统风格一致
- `JavaFX`：官方退出最新GUI开发包

# Swing

紫色部分是`AWT`，绿色部分是`Swing`:

![](https://cdn.jsdelivr.net/gh/guosonglu/images@master/blog-img/20221215134445.png)

简单示例：

```java
/**
 * Swing
 *
 * @author luguosong
 * @date 2022/12/15
 */
public class SwingDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("加法计算");
        //设置位置尺寸
        frame.setBounds(300, 300, 600, 200);
        //设置布局
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        //设置窗口关闭程序退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //字体
        Font font = new Font("微软雅黑", Font.PLAIN, 18);

        //文本输入框
        JTextField tf1 = new JTextField(5);
        tf1.setFont(font);
        frame.add(tf1);

        //加号
        JLabel addLabel = new JLabel("+");
        addLabel.setFont(font);
        frame.add(addLabel);

        //文本输入框
        JTextField tf2 = new JTextField(5);
        tf2.setFont(font);
        frame.add(tf2);

        //等号
        JLabel equalLabel = new JLabel("=");
        equalLabel.setFont(font);
        frame.add(equalLabel);

        //结果
        JLabel resultLabel = new JLabel("");
        resultLabel.setFont(font);
        frame.add(resultLabel);

        //计算按钮
        JButton jButton = new JButton("计算");
        jButton.setFont(font);
        jButton.addActionListener((env) -> {
            try {
                int i1 = Integer.parseInt(tf1.getText());
                int i2 = Integer.parseInt(tf2.getText());
                resultLabel.setText(i1 + i2 + "");
            } catch (Exception e) {
                //创建模态对话框
                JDialog dialog = new JDialog(frame, true);
                //标题
                dialog.setTitle("警告");
                //设置提示框位置居中
                dialog.setBounds(frame.getX() + ((frame.getWidth() - 300) >> 1),
                        frame.getY() + ((frame.getHeight() - 100) >> 1),
                        300,
                        100);
                //设置对话库不可以修改大小
                dialog.setResizable(false);
                dialog.add(new JLabel("输入格式错误"));
                dialog.setVisible(true);
            }
        });
        frame.add(jButton);

        //显示窗口
        frame.setVisible(true);
    }
}
```
