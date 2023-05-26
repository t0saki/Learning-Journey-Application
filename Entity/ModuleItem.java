package Entity;

import Control.UserInfoHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       the item panel showed on the screen
 *       indicating a module
 *       contains module's name and score
 */
public class ModuleItem extends ClickableItem {
    private UserInfoHandler userInfoHandler;
    private int num;
    private int Mark;

    private String moduleName;
    private int gradientWidth;

    public ModuleItem(int col, String studentID) {
        super();
        userInfoHandler = getUserInfo();
        userInfoHandler.open("Data/Modules&Marks.csv");

        JLabel label1 = new JLabel();
        // JLabel label2 = new JLabel();
        moduleName = getItemName(col);
        Mark = getModuleMark(studentID, moduleName);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(150, 100));
        // this.setSize(150, 100);
        if (Mark < 60) {
            this.setBackground(Color.red);
        } else if (Mark > 60 && Mark <= 70) {
            this.setBackground(Color.orange);
        } else if (Mark > 70 && Mark <= 80) {
            this.setBackground(Color.yellow);
        } else if (Mark > 80 && Mark <= 90) {
            this.setBackground(Color.CYAN);
        } else {
            this.setBackground(Color.GREEN);
        }

        label1.setText("<html><center>" + moduleName + "<br>" + String.valueOf(Mark) + "</center></html>");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        // label2.setText(String.valueOf(Mark));
        // setGradientWidth(Mark);
        // repaint();
        // Font font = new Font("Arial", Font.BOLD, 16);
        // label1.setFont(font);
        // label1.setOpaque(true);
        int padding = 1;
        Border customBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        label1.setBorder(customBorder);
        // label2.setFont(font);

        // int fillWidth = Mark / 100*getWidth();
        // JPanel fillPanel = new JPanel();
        // fillPanel.setBackground(Color.BLUE);
        // fillPanel.setPreferredSize(new Dimension(fillWidth, getHeight() - 2 *
        // padding));

        this.add(Box.createVerticalGlue());
        this.add(label1);
        this.add(Box.createVerticalStrut(10));
        // this.add(label2);
        // add(fillPanel);
        this.add(Box.createVerticalGlue());
        // label1.setSize( 150, 45);
        // label2.setSize(150, 45);
        // label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        // label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        String str = moduleName + " " + String.valueOf(Mark);
        details = str;
        this.setVisible(true);
    }

    public String getItemName(int col) {
        return userInfoHandler.getHeaders()[col];
    }

    public int getModuleMark(String studentID, String moduleName) {
        int rowIndex = userInfoHandler.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        return Integer.parseInt(userInfoHandler.getElement(moduleName, rowIndex));
    }

    public UserInfoHandler getUserInfo() {
        return new UserInfoHandler();
    }

    public int getNum() {
        num = userInfoHandler.getHeaders().length - 1;
        return num;
    }
    // @Override
    // public void repaint(){
    //
    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // // Create a gradient paint
        //// GradientPaint gradient = new GradientPaint(
        //// 0, 0, Color.PINK, getWidth(), getHeight(), Color.orange);
        // GradientPaint gradient = new GradientPaint(
        // 0, 0, Color.BLUE, getWidth(), getHeight(), Color.CYAN);
        // // Create a graphics2D object from the graphics object
        // Graphics2D g2d = (Graphics2D) g;
        //
        // // Set the paint to be the gradient
        // g2d.setPaint(gradient);
        //
        // // Draw a rectangle filled with the gradient
        // g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw a border around the panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    public void setGradientWidth(int width) {
        gradientWidth = width / 100;
        repaint();
    }

    public int getValue() {
        return Mark;
    }

    public String getItemName() {
        return moduleName;
    }
}
