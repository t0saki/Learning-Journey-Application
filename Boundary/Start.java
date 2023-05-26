package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Start extends JFrame {
    public Start() {
        setTitle("Start");

        setSize(1000, 650);

        setLocationRelativeTo(null);

        setUndecorated(true);

        // Create a JPanel object and set its layout to GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        // Set the background color of the JPanel object
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
        backgroundLabel.add(panel);

        // Set the layout of the content pane of the JFrame to OverlayLayout
        setLayout(new OverlayLayout(getContentPane()));

        getContentPane().add(backgroundLabel);

        setVisible(true);
    }

}