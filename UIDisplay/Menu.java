package UIDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu{
    JFrame frame = new JFrame("Menu");
    // Create a sidebar
    JPanel sidebarPanel = new JPanel();
    // Create a content panel
    JPanel contentPanel = new JPanel();
    public Menu() {
        // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        // Set the sidebar layout
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));

        // Add the sidebar and content panel to the frame
        contentPanel.setSize(800, 600);
        frame.getContentPane().add(sidebarPanel, BorderLayout.WEST);
        frame.getContentPane().add(contentPanel, BorderLayout.CENTER);
//        int sidebarWidth = frame.getWidth() / 3;
//        sidebarPanel.setPreferredSize(new Dimension(sidebarWidth, frame.getHeight()));
//        contentPanel.setPreferredSize(new Dimension(frame.getWidth() - sidebarWidth, frame.getHeight()));

        addDisplay(new UserInfo());
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
                // Add the clicked item's panel to the content panel
                contentPanel.add(display.getContentPanel());
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
