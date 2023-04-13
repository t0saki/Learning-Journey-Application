package UIDisplay;

import javax.swing.*;
import java.awt.*;

public class BaseDisplay extends JPanel{
    JPanel contentPanel = new JPanel();
    String displayType = "Base";
    public BaseDisplay(String type) {
        displayType = type;
        // Create a sidebar logo with JLabel
        JLabel logo = new JLabel(displayType);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        // Clear and add
        removeAll();
        add(logo);
        setSize(300,100);
        contentPanel.setSize(800, 600);
    }

    public JPanel getSideItemPanel() {
        return this;
    }

    public JPanel getContentPanel() {
        return contentPanel;
    }
}
