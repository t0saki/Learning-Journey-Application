package Boundary;

import javax.swing.*;

import Entity.FontManager;
import java.awt.*;

public class BaseDisplay extends JPanel {
    public static Color unselectedColor = new Color(134, 91, 154);
    public static Color selectedColor = new Color(74, 46, 104);

    JPanel contentPanel = new JPanel();
    String displayType = "Base";

    public BaseDisplay(String type) {
        displayType = type;
        // Create a sidebar logo with JLabel
        JLabel logo = new JLabel(displayType);
        logo.setFont(FontManager.getLatoRegular(16));
        logo.setForeground(Color.WHITE);

        // this layout allows the logo to be centered
        setLayout(new GridBagLayout());
        add(logo);

        setBackground(unselectedColor);
    }

    public JPanel getContentPanel() {
        // Print panel size
        System.out.println(contentPanel.getSize());

        contentPanel.setSize(800, 600);

        return contentPanel;
    }
}
