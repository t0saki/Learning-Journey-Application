package Boundary;

import javax.swing.*;
import java.awt.*;

public class BaseDisplay extends JPanel {
    JPanel contentPanel = new JPanel();
    String displayType = "Base";

    public BaseDisplay(String type) {
        displayType = type;
        // Create a sidebar logo with JLabel
        JLabel logo = new JLabel(displayType);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        // create a blue border for logo
        logo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

        // add logo, set it to vertical center and horizontal center of the sidebar
        setLayout(new GridBagLayout());

        add(logo);

        // Set background color
        setBackground(Color.GRAY);
    }

    public JPanel getSideItemPanel() {
        return this;
    }

    public JPanel getContentPanel() {
        // Print panel size
        System.out.println(contentPanel.getSize());

        contentPanel.setSize(800, 600);

        return contentPanel;
    }
}
