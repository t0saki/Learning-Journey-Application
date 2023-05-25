package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame {
    private JButton startButton;

    public Start() {
        // 设置窗口标题
        setTitle("Start");

        // 设置窗口大小
        setSize(1000, 800);

        // 设置窗口居中
        setLocationRelativeTo(null);

        // 设置关闭按钮的默认操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板，并设置为GridBagLayout布局
        JPanel panel = new JPanel(new GridBagLayout());

        // 创建标题标签
        JLabel titleLabel = new JLabel("标题");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // 设置字体

        // 创建按钮
        startButton = new JButton("Start");

        // 添加按钮的点击事件监听器
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 在这里编写点击按钮后的逻辑
            }
        });

        // 创建GridBagConstraints对象，用于设置组件的约束条件
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE; // 不拉伸组件
        gbc.anchor = GridBagConstraints.CENTER; // 组件在单元格中居中对齐

        // 设置标题标签的约束条件
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 0, 0); // 设置上方留白
        panel.add(titleLabel, gbc);

        // 设置按钮的约束条件
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 0, 0, 0); // 设置上方留白
        panel.add(startButton, gbc);

        // 设置背景图片
        ImageIcon backgroundImg = new ImageIcon("C://Users//90899//Desktop//微信图片_20230525195259.png");
        JLabel backgroundLabel = new JLabel(backgroundImg);

        // 将面板和背景图片添加到窗口中
        getContentPane().add(backgroundLabel);
        getContentPane().add(panel);

        // 设置布局为层叠布局
        setLayout(new OverlayLayout(getContentPane()));

        // 设置窗口可见
        setVisible(true);
    }
}