package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;
import java.awt.*;

public class ModuleItem extends JPanel {
    private UserInfoHandler userInfoHandler;
    private int num;

    public ModuleItem(int col, String name) {
        userInfoHandler = getUserInfo();
        userInfoHandler.open("Data/Modules&Marks.csv");

        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        String ModuleName = getModuleName(col);
        int Mark = getModuleMark(name, ModuleName);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(150, 100));
        // this.setSize(150, 100);
        this.setBackground(Color.LIGHT_GRAY);
        label1.setText(ModuleName);
        label2.setText(String.valueOf(Mark));
        Font font = new Font("Arial", Font.BOLD, 16);
        label1.setFont(font);
        label2.setFont(font);
        this.add(Box.createVerticalGlue());
        this.add(label1);
        this.add(Box.createVerticalStrut(10));
        this.add(label2);
        this.add(Box.createVerticalGlue());
        // label1.setSize( 150, 45);
        // label2.setSize(150, 45);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setVisible(true);
    }

    public String getModuleName(int col) {
        return userInfoHandler.getHeaders()[col];
    }

    public int getModuleMark(String name, String module) {
        int rowIndex = userInfoHandler.getFirstRowIndexByHeaderAndVal("Name", name);
        return Integer.parseInt(userInfoHandler.getElement(module, rowIndex));
    }

    public UserInfoHandler getUserInfo() {
        return new UserInfoHandler();
    }

    public int getNum() {
        num = userInfoHandler.getHeaders().length - 1;
        return num;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Create a gradient paint
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.PINK, getWidth(), getHeight(), Color.orange);

        // Create a graphics2D object from the graphics object
        Graphics2D g2d = (Graphics2D) g;

        // Set the paint to be the gradient
        g2d.setPaint(gradient);

        // Draw a rectangle filled with the gradient
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw a border around the panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
