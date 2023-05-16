package Boundary;

import Control.Operate;
import Control.UserInfoHandler;
import Entity.ModuleItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo(String studentID) {
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

        JButton btn1 = new JButton("Standard calculation method");
        JButton btn2 = new JButton("Simple 4-point scale algorithm");
        JButton btn3 = new JButton("Peking University GPA Algorithm");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 1);
                showGPA(GPA, GPAscorePanel,1);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 2);
                showGPA(GPA, GPAscorePanel,2);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double GPA = Operate.GPAhandler(studentID, 3);
                showGPA(GPA, GPAscorePanel,3);
                GPAlabel.setText("GPA: " + String.format("%.2f", GPA));
            }
        });

        buttonpanel.add(btn1);
        buttonpanel.add(btn2);
        buttonpanel.add(btn3);
        GPApanel.add(GPAscroller);

        this.setLayout(new BorderLayout());

        JLabel[] labels = new JLabel[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            labels[i] = new JLabel(data[i]);
        }

        // Show on this JPanel
        for (JLabel label : labels) {
            title.add(label);
        }
        userPanel.add(title);
        userPanel.add(GPApanel);
        userPanel.add(buttonpanel);

        add(userPanel, "North");

        JPanel ItemPanel = new JPanel();
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        ModuleItem firstModule = new ModuleItem(1, studentID);
        ModuleItem[] moduleItem = new ModuleItem[firstModule.getNum()];
        moduleItem[0] = firstModule;
        ItemPanel.add(firstModule);
        for (int i = 1; i < firstModule.getNum(); i++) {
            moduleItem[i] = new ModuleItem(i + 1, studentID);
            ItemPanel.add(moduleItem[i]);
        }
        JScrollPane scrollPane = new JScrollPane(ItemPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, "Center");
    }

    private void showGPA(double GPA, JPanel GPAscorePanel, int type) {
        if(type!=1) {
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
        }else{
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