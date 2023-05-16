package Entity;

import Control.BaseHandler;

import javax.swing.*;
import java.awt.*;

public class RoleItem extends JPanel {
    BaseHandler baseHandler;
    public RoleItem(String studentID,int index){
        baseHandler=new BaseHandler();
        baseHandler.open("Data/roles/"+studentID+".csv");
        JLabel content=new JLabel();
        String str=baseHandler.getElement("Roles",index);
        content.setText(str);
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
