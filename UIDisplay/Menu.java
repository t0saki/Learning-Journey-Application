package UIDisplay;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
    JFrame frame = new JFrame("Menu");
    // Create a sidebar
    JPanel sidebarPanel = new JPanel();
    // Create a content panel
    JPanel contentPanel = new JPanel();
//    JPanel contentPanel = new UserInfo();

    public Menu() {
        // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set the sidebar layout
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Add the sidebar and content panel to a split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);
        splitPane.setDividerLocation(150);
        frame.getContentPane().add(splitPane);

        addDisplay(new UserInfoPanel());
        addDisplay(new TestPanel());
    }

    public void addDisplay(BaseDisplay display) {
        // Add the sidebar item to the sidebar
        sidebarPanel.add(display.getSideItemPanel());

        // Add the listener
        display.getSideItemPanel().addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Remove any existing content from the content panel
                contentPanel.removeAll();

                JPanel panel = display.getContentPanel();
                panel.setMinimumSize(contentPanel.getSize());
                contentPanel.add(panel);

                // Repaint the content panel to reflect the change
                contentPanel.revalidate();
                contentPanel.repaint();
            }
        });

        // Refresh the sidebar
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
}
