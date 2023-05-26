package Entity;

import Control.BaseHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       the item panel showed on the screen
 *       indicating an activity
 */
public class CurriculumItem extends ClickableItem {
    BaseHandler baseHandler;

    public CurriculumItem(String studentID, int index) {
        super();
        baseHandler = new BaseHandler();
        baseHandler.open("Data/Curriculum/" + studentID + ".csv");
        JLabel content = new JLabel();
        String str = baseHandler.getElement("Curriculum", index);
        content.setText(str);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(Color.LIGHT_GRAY);

        content.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createVerticalGlue());
        this.add(content);
        this.add(Box.createVerticalGlue());
        this.setVisible(true);
        baseHandler.close();
        details = str;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Create a gradient paint
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(144, 238, 144), getWidth(), getHeight(), Color.white);

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
