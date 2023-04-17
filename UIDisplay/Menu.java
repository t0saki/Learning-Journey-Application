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

        // Create a content panel from UserInfo
        UserInfo contentPanel = new UserInfo();
        JScrollPane UserInfoPanel=new JScrollPane(contentPanel);
        UserInfoPanel.setPreferredSize(new Dimension(700, 600));
        UserInfoPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Add the sidebar and content panel to this JFrame
        add(sidebarScrollPane, "West");
        add(UserInfoPanel, "Center");

        // Show the JFrame
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setVisible(true);

    }
}
