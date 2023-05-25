package Boundary;

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
    private String studentID;

    public Menu(String sID) {
        studentID = sID;

        // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // bg
        // sidebarPanel.setBackground(new Color(245, 245, 245));
        // sidebarPanel.setLayout(new GridBagLayout());
        //
        // contentPanel.setBackground(Color.WHITE);
        // contentPanel.setLayout(new BorderLayout());
        // Set the sidebar layout

        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(BaseDisplay.unselectedColor);

        // Add the sidebar and content panel to a split pane
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);
        splitPane.setDividerLocation(320);
        frame.getContentPane().add(splitPane);

        addDisplay(new UserInfoPanel(studentID));
        addDisplay(new AchievementPanel(studentID));
        addDisplay(new skillPanel(studentID));
        addDisplay(new ActivityPanel(studentID));
        addDisplay(new PortfoliosPanel(studentID));
        addDisplay(new RolePanel(studentID));
        addDisplay(new PlanPanel(studentID));
        addDisplay(new CourseSchedulePanel(studentID));
    }

    // Add target display to the sidebar, set for its listeners
    public void addDisplay(BaseDisplay display) {
        JPanel sideItemPanel = display;

        sideItemPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sideItemPanel.setBackground(BaseDisplay.selectedColor);
            }

            public void mouseExited(MouseEvent e) {
                sideItemPanel.setBackground(BaseDisplay.unselectedColor);
            }
        });

        display.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Remove content panel from split pane
                splitPane.remove(contentPanel);
                
                // Set the new content panel
                contentPanel = new JScrollPane(display.getContentPanel());

                // contentPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                // contentPanel.setWheelScrollingEnabled(true);
                // contentPanel.getVerticalScrollBar().setUnitIncrement(50);
                // Add the new content panel to the split pane
                
                splitPane.add(contentPanel);
            }
        });

        // GridBagConstraints gbc = new GridBagConstraints();
        // gbc.fill = GridBagConstraints.HORIZONTAL;
        // gbc.weightx = 1.0;
        // gbc.gridx = 0;
        // gbc.gridy = GridBagConstraints.RELATIVE;
        // gbc.insets = new Insets(10, 10, 10, 10);
        sideItemPanel.setPreferredSize(new Dimension(320, 50));
        sidebarPanel.add(sideItemPanel);

        // `> seems unnecessary
        // sidebarPanel.revalidate();
        // sidebarPanel.repaint();
        // frame.revalidate();
        // frame.repaint();
    }
}
