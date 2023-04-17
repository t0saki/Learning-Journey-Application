package UIDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {
    JFrame frame = new JFrame("Menu");
    // Create a sidebar
    JPanel sidebarPanel = new JPanel();
    // Create a content panel
    JScrollPane contentPanel = new JScrollPane();
    JSplitPane splitPane;
    // JPanel contentPanel = new UserInfo();

    public Menu() {
        // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Set the sidebar layout
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Add the sidebar and content panel to a split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);
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
                // Remove content panel from split pane
                splitPane.remove(contentPanel);
                // Set the new content panel
                contentPanel = new JScrollPane(display.getContentPanel());
                contentPanel.setPreferredSize(new Dimension(700, 600));
                contentPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                contentPanel.setWheelScrollingEnabled(true);
                contentPanel.getVerticalScrollBar().setUnitIncrement(50);
                // Add the new content panel to the split pane
                splitPane.add(contentPanel);

                // Refresh the split pane
                splitPane.revalidate();
                splitPane.repaint();
                // Refresh the frame
                frame.revalidate();
                frame.repaint();
            }
        });

        // Refresh the sidebar
        sidebarPanel.revalidate();
        sidebarPanel.repaint();
        frame.revalidate();
        frame.repaint();
    }
}
