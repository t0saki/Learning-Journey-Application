package Entity;

import Control.UserInfoHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * @author XiangzheKong
 * @author GuoYu
 * @date 2023/05/25
 *       the item panel showed on the screen
 *       indicating a module
 *       contains module's name and score
 */
public class ModuleItem extends ClickableItem implements BaseItem{
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
        JLabel label2 = new JLabel();
        moduleName = getItemName(col);
        Mark = getModuleMark(studentID, moduleName);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(150, 100));
        // this.setSize(150, 100);
        if (Mark < 60) {
            label2.setText("<html><sup>Fail</sup></html>");
        } else if (Mark > 60 && Mark <= 70) {
            label2.setText("<html><sup>Pass</sup></html>");
        } else if (Mark > 70 && Mark <= 80) {
            label2.setText("<html><sup>Good</sup></html>");
        } else if (Mark > 80 && Mark <= 90) {
            label2.setText("<html><sup>Merit</sup></html>");
        } else {
            label2.setText("<html><sup>Excellent</sup></html>");
            //this.setBackground(Color.GREEN);
        }

        label1.setText("<html><center>" + moduleName + "<br>" + String.valueOf(Mark) + "</center></html>");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        //label2.setText("<html><sup>111</sup></html>");
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
        this.add(label2);
        this.add(Box.createVerticalGlue());
        this.add(label1);
        this.add(Box.createVerticalStrut(10));

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
        int width = getWidth();
        int height = getHeight();

        if (Mark < 60) {
            //label2.setText("<html><sup>Fail</sup></html>");
            GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 158, 158), width, height, new Color(255, 192, 144));
            ((Graphics2D) g).setPaint(gradient);
        } else if (Mark > 60 && Mark <= 70) {
            //label2.setText("<html><sup>Pass</sup></html>");
            GradientPaint gradient = new GradientPaint(0, 0, new Color(250, 202, 190), width, height, new Color(247, 226, 180));
            ((Graphics2D) g).setPaint(gradient);
        } else if (Mark > 70 && Mark <= 90) {
            //label2.setText("<html><sup>Good</sup></html>");
            GradientPaint gradient = new GradientPaint(0, 0, new Color(177, 215, 180), width, height, new Color(248, 249, 136));
            ((Graphics2D) g).setPaint(gradient);
        } else {
            //label2.setText("<html><sup>Excellent</sup></html>");
            GradientPaint gradient = new GradientPaint(0, 0, new Color(127, 183, 126), width, height, new Color(192, 238, 228));
            ((Graphics2D) g).setPaint(gradient);
        }
         // Create a gradient paint
        // GradientPaint gradient = new GradientPaint(
        // 0, 0, Color.PINK, getWidth(), getHeight(), Color.orange);
//         GradientPaint gradient = new GradientPaint(
//         0, 0, Color.CYAN, getWidth(), getHeight(), Color.BLUE);
//         // Create a graphics2D object from the graphics object
//         Graphics2D g2d = (Graphics2D) g;
//
//         // Set the paint to be the gradient
//         g2d.setPaint(gradient);
//
         // Draw a rectangle filled with the gradient
         g.fillRect(0, 0, getWidth(), getHeight());

         //Draw a border around the panel
        g.setColor(Color.WHITE);
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
