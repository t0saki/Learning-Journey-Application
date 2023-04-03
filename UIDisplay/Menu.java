package UIDisplay;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
    // Just show a simple menu
    public Menu() {
        // Create a new JFrame
        setTitle("My Application");

        // Create a sidebar
        JList<String> sidebarList = new JList<>(new String[]{"Home", "Profile", "Settings"});
        JScrollPane sidebarScrollPane = new JScrollPane(sidebarList);

        // Create a content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Hello World!"), BorderLayout.CENTER);

        // Add the sidebar and content panel to the JFrame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarScrollPane, contentPanel);
        add(splitPane);

        // Show the JFrame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
