package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame {
    public Start() {
        // 设置窗口标题
        setTitle("Start");

        // 设置窗口大小
        setSize(1000, 650);

        // 设置窗口居中
        setLocationRelativeTo(null);

        // 去掉窗口的默认装饰（包括标题栏和关闭按钮）
        setUndecorated(true);

        // 创建面板，并设置为 GridBagLayout 布局
        JPanel panel = new JPanel(new GridBagLayout());

        // 设置背景图片
        ImageIcon backgroundImg = new ImageIcon("Data//pic//start_waifu2x_4x_3n_png_shrink.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImg);
        backgroundLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Login();
                // System.out.println("Image clicked!");
                // dispose();
            }
        });
        // 将面板添加到背景图片标签上
        backgroundLabel.add(panel);

        // 设置布局为层叠布局
        setLayout(new OverlayLayout(getContentPane()));

        // 将背景图片标签添加到窗口中
        getContentPane().add(backgroundLabel);

        // 设置窗口可见
        setVisible(true);
    }

}