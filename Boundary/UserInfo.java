package Boundary;

import Control.FontManager;
import Control.Operate;
import Control.StringSearch;
import Control.UserInfoHandler;
import Entity.ModuleItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author XiangzheKong
 * @author GuoYu
 * @date 2023/05/25
 *       the detailed panel showcasing user's GPA,
 *       modules and scores
 */
public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo(String studentID, HistogramPanel histogramPanel) {
        setPreferredSize(new Dimension(680, 1600));
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(3, 1, 5, 5));
        JPanel title = new JPanel();
        JPanel GPApanel = new JPanel();
        GPApanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(1, 3, 5, 5));

        StringSearch stringSearch = new StringSearch();

        JLabel GPAlabel = new JLabel("GPA: ");
        JPanel GPAcontainer = new JPanel();
        GPAcontainer.setPreferredSize(new Dimension(600, 200));
        GPAcontainer.setBackground(Color.WHITE);
        GPAcontainer.setLayout(new BorderLayout());
        JPanel GPAscorePanel = new JPanel();
        GPAscorePanel.setPreferredSize(new Dimension(0, 150));
        GPAscorePanel.setBackground(Color.LIGHT_GRAY);
        GPAcontainer.add(GPAscorePanel, BorderLayout.NORTH);
        GPAcontainer.add(GPAlabel, BorderLayout.CENTER);
        JScrollPane GPAscroller = new JScrollPane(GPAcontainer);

        // three different methods calculating GPA
        JButton btn1 = new JButton("<html><center>Standard<br>calculation<br>method</center></html>");
        JButton btn2 = new JButton("<html><center>Simple 4-point scale <br> algorithm</center></html>");
        JButton btn3 = new JButton("<html><center>Peking University <br> GPA Algorithm</center></html>");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 1);
                showGPA(GPA, GPAscorePanel, 1);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 2);
                showGPA(GPA, GPAscorePanel, 2);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 3);
                showGPA(GPA, GPAscorePanel, 3);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });

        buttonpanel.add(btn1);
        buttonpanel.add(btn2);
        buttonpanel.add(btn3);
        GPApanel.add(GPAscroller);

        this.setLayout(new BorderLayout());

        // Generate user's information
        String[] str = new String[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            if (i >= 2) {
                str[i] = data[i + 1];
            } else {
                str[i] = data[i];
            }
        }

        // Show on this JPanel
        // StringBuilder stringBuilder = new StringBuilder();
        // stringBuilder.append("<html><head>");
        // stringBuilder.append("<link
        // href=\"https://fonts.googleapis.com/css2?family=Alex+Brush&display=swap\"
        // rel=\"stylesheet\">");
        // stringBuilder.append("<style>");
        // //stringBuilder.append("body { font-family: Arial, sans-serif; }");
        // stringBuilder.append("h1 { font-family: 'Alex Brush', cursive; font-size:
        // 50px; text-align: center; }");
        // stringBuilder.append("p { font-size: 20px; }");
        // stringBuilder.append("</style>");
        // stringBuilder.append("</head><body>");
        // stringBuilder.append("<h1>Personal Information</h1>");
        // stringBuilder.append("</body></html>");

        // stringBuilder.append("<p><strong>Name:</strong> " + str[0] + "</p>");
        // stringBuilder.append("<p><strong>Student ID:</strong> " + str[1] + "</p>");
        // stringBuilder.append("<p><strong>Major:</strong> " + str[2] + "</p>");
        // stringBuilder.append("<p><strong>Entrance year:</strong> " + str[3] +
        // "</p>");
        // stringBuilder.append("</body></html>");

        JLabel titlelable = new JLabel("<html><center>Personal Information</center></html>");
        titlelable.setFont(FontManager.getIBSCartooning(110));
        titlelable.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        // userPanel.add(titlelable);
        // titlelable.setFont(new Font("", Font.PLAIN, 30));
        title.add(titlelable);
        userPanel.add(title);
        userPanel.add(GPApanel);
        userPanel.add(buttonpanel);

        add(userPanel, "North");

        JPanel ItemPanel = new JPanel();
        // ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.Y_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        ModuleItem firstModule = new ModuleItem(1, studentID);
        ModuleItem[] moduleItem = new ModuleItem[firstModule.getNum()];
        moduleItem[0] = firstModule;
        int[] marks = new int[firstModule.getNum()];
        ItemPanel.add(firstModule);
        marks[0] = firstModule.getMark();
        stringSearch.addEntry(firstModule.getModuleName());
        for (int i = 1; i < firstModule.getNum(); i++) {
            moduleItem[i] = new ModuleItem(i + 1, studentID);
            ItemPanel.add(moduleItem[i]);
            marks[i] = moduleItem[i].getMark();
            stringSearch.addEntry(moduleItem[i].getModuleName());
        }

        histogramPanel.update(new Histogram(marks, 10));
        // JScrollPane scrollPane = new JScrollPane(ItemPanel);
        // scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(ItemPanel, "Center");

        stringSearch.searchKeyword();
    }

    private void showGPA(double GPA, JPanel GPAscorePanel, int type) {
        if (type != 1) {
            GPAscorePanel.removeAll();
            GPAscorePanel.revalidate();
            GPAscorePanel.repaint();

            int width = (int) (GPA / 4.0 * GPAscorePanel.getWidth());
            JLabel scoreLabel = new JLabel();
            scoreLabel.setOpaque(true);
            scoreLabel.setBackground(Color.GREEN);
            scoreLabel.setPreferredSize(new Dimension(width, GPAscorePanel.getHeight()));

            JPanel scoreContainer = new JPanel();
            scoreContainer.setLayout(new BorderLayout());
            scoreContainer.setBackground(Color.LIGHT_GRAY);
            scoreContainer.add(scoreLabel, BorderLayout.WEST);

            GPAscorePanel.setLayout(new BorderLayout());
            GPAscorePanel.add(scoreContainer, BorderLayout.WEST);
            GPAscorePanel.revalidate();
            GPAscorePanel.repaint();
        } else {
            GPAscorePanel.removeAll();
            GPAscorePanel.revalidate();
            GPAscorePanel.repaint();

            int width = (int) (GPA / 100.0 * GPAscorePanel.getWidth());
            JLabel scoreLabel = new JLabel();
            scoreLabel.setOpaque(true);
            scoreLabel.setBackground(Color.GREEN);
            scoreLabel.setPreferredSize(new Dimension(width, GPAscorePanel.getHeight()));

            JPanel scoreContainer = new JPanel();
            scoreContainer.setLayout(new BorderLayout());
            scoreContainer.setBackground(Color.LIGHT_GRAY);
            scoreContainer.add(scoreLabel, BorderLayout.WEST);

            GPAscorePanel.setLayout(new BorderLayout());
            GPAscorePanel.add(scoreContainer, BorderLayout.WEST);
            GPAscorePanel.revalidate();
            GPAscorePanel.repaint();
        }
    }
}