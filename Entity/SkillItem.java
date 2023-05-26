package Entity;

import Control.BaseHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author XiangzheKong
 * @author GuoYu
 * @date 2023/05/25
 *       the item panel showed on the screen
 *       indicating a skill
 */
public class SkillItem extends ClickableItem implements BaseItem {
    BaseHandler baseHandler;
    String item;

    public SkillItem(String studentID, int index) {
        super();
        baseHandler = new BaseHandler();
        baseHandler.open("Data/Skills/" + studentID + ".csv");
        JLabel content = new JLabel();
        String str = baseHandler.getElement("Skills", index);
        content.setText(str);
        item = str;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(600, 100));
        this.setBackground(Color.LIGHT_GRAY);
        Font font = new Font("Arial", Font.BOLD, 16);
        content.setFont(font);
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
                0, 0, new Color(244, 208, 63), getWidth(), getHeight(), new Color(247, 220, 111));

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

    public String getItemName() {
        return item;
    }
}
