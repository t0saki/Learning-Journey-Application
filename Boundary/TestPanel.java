package Boundary;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends BaseDisplay {
    public TestPanel() {
        super("Test Panel");
        // Add something to contentPanel
        contentPanel.add(new JLabel("content"));
        // Set background color
        setBackground(Color.RED);
    }
}
