package Boundary;

import javax.swing.*;

import Entity.FontManager;
import java.awt.*;
import javax.swing.border.*;
// import java.awt.geom.*;

public class BaseDisplay extends JPanel {
    public static Color unselectedColor = new Color(134, 91, 154);
    public static Color selectedColor = new Color(74, 46, 104);

    JPanel contentPanel = new JPanel();
    String displayType = "Base";

    public BaseDisplay(String type) {
        // Add some paddings to the sidebar
        displayType = "    " + type;

        // Create a sidebar logo with JLabel
        JLabel logo = new JLabel(displayType);
        logo.setFont(FontManager.getLatoRegular(16));
        logo.setForeground(Color.WHITE);

        setLayout(new GridLayout(0, 1));

        // debug
        // logo.setBorder(new MatteBorder(15, 15, 15, 15, BaseDisplay.unselectedColor));
        // logo.setBorder(BorderFactory.createLineBorder(Color.RED));

        // this allows its content to be centered
        // setLayout(new GridBagLayout());
        add(logo);

        setBorder(new MatteBorder(15, 15, 15, 15, BaseDisplay.unselectedColor));
        setBackground(unselectedColor);

    }

    public JPanel getContentPanel() {
        // Print panel size
        System.out.println(contentPanel.getSize());

        contentPanel.setSize(800, 600);

        return contentPanel;
    }

    // @Override
    // public void paint(Graphics g) {
    // int fieldX = 0;
    // int fieldY = 0;
    // int fieldWeight = getSize().width;
    // int fieldHeight = getSize().height;
    // Area borderRegion = new Area(new Rectangle(0, 0, fieldWeight, fieldHeight));

    // RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX + 15, fieldY + 15,
    // fieldWeight - 30, fieldHeight - 30, 8, 8);

    // borderRegion.subtract(new Area(rect));

    // g.setColor(selectedColor);
    // g.fillRoundRect(fieldX + 15, fieldY + 15,
    // fieldWeight - 30, fieldHeight - 30, 8, 8);

    // super.paint(g);
    // }
}
