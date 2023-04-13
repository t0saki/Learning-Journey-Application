package UIDisplay;

import javax.swing.*;

public class TestPanel extends BaseDisplay{
    public TestPanel() {
        super("Test Panel");
        // Add something to contentPanel
        contentPanel.add(new JLabel("content"));
    }
}
