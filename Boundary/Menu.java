package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Control.GlobalColors;

/**
 * @author Zhengxiao Wu
 * @author Ruitian Yang
 * @date 2023/05/25
 *       the menu panel, containing sidebar and content panel
 *       can be used to switch between different panels
 */
public class Menu {
    JFrame frame = new JFrame("Menu");
    // Create a sidebar
    JPanel sidebarPanel = new JPanel();
    // Create a content panel
    JScrollPane contentPanel = new JScrollPane();
    PersonalInformationPanel personalInformationPanel;

    JSplitPane splitPane1;
    JSplitPane splitPane2;

    // JPanel contentPanel = new UserInfo();
    private String studentID;

    public Menu(String sID) {
        studentID = sID;
        personalInformationPanel = new PersonalInformationPanel(sID);

        // Create a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
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
        sidebarPanel.setBackground(GlobalColors.lighterPurple);

        // Add the sidebar and content panel to a split pane
        splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanel, contentPanel);
        splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, personalInformationPanel);

        splitPane1.setDividerLocation(300);
        splitPane2.setDividerLocation(1600);

        frame.getContentPane().add(splitPane2);

        HistogramPanel histogramPanel = new HistogramPanel();
        UserInfoPanel userInfoPanel = new UserInfoPanel(studentID, histogramPanel);

        addDisplay(userInfoPanel);
        addDisplay(histogramPanel);
        addDisplay(new AchievementPanel(studentID));
        addDisplay(new skillPanel(studentID));
        addDisplay(new CurriculumPanel(studentID));
        addDisplay(new PortfoliosPanel(studentID));
        addDisplay(new RolePanel(studentID));
        addDisplay(new PlanPanel(studentID));
        addDisplay(new CourseSchedulePanel(studentID));

        BackPanel backPanel = new BackPanel();
        backPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                new Login();
            }
        });
        addDisplay(backPanel);

        // Set default content panel
        splitPane1.remove(contentPanel);
        contentPanel = new JScrollPane(userInfoPanel.getContentPanel());
        splitPane1.add(contentPanel);
    }

    // Add target display to the sidebar, set for its listeners
    public void addDisplay(BaseDisplay display) {
        JPanel sideItemPanel = display;

        sideItemPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                sideItemPanel.setBackground(GlobalColors.darkerPurple);
            }

            public void mouseExited(MouseEvent e) {
                sideItemPanel.setBackground(GlobalColors.lighterPurple);
            }
        });

        display.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // Remove content panel from split pane
                splitPane1.remove(contentPanel);

                // Set the new content panel
                contentPanel = new JScrollPane(display.getContentPanel());

                // contentPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                // contentPanel.setWheelScrollingEnabled(true);
                // contentPanel.getVerticalScrollBar().setUnitIncrement(50);
                // Add the new content panel to the split pane

                splitPane1.add(contentPanel);
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
