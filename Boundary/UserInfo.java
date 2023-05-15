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
        userPanel.setLayout(new GridLayout(3,1,5,5));
        JPanel title=new JPanel();
        JPanel GPApanel=new JPanel();
        GPApanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel buttonpanel=new JPanel();
        buttonpanel.setLayout(new GridLayout(1,3,5,5));

        JLabel GPAlabel=new JLabel();
        JButton btn1=new JButton("Standard calculation method");
        JButton btn2=new JButton("Simple 4-point scale algorithm");
        JButton btn3=new JButton("Peking University GPA Algorithm");

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Standard calculation method  Your GPA is : ");
                double GPA= Operate.GPAhandler(studentID,1);
                stringBuilder.append(GPA);
                String s=stringBuilder.toString();
                GPAlabel.setText(s);
                revalidate();
                repaint();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Simple 4-point scale algorithm  Your GPA is : ");
                double GPA= Operate.GPAhandler(studentID,2);
                stringBuilder.append(GPA);
                String s=stringBuilder.toString();
                GPAlabel.setText(s);
                revalidate();
                repaint();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append("Peking University GPA Algorithm  Your GPA is : ");
                double GPA= Operate.GPAhandler(studentID,3);
                stringBuilder.append(GPA);
                String s=stringBuilder.toString();
                GPAlabel.setText(s);
                revalidate();
                repaint();
            }
        });

        buttonpanel.add(btn1);
        buttonpanel.add(btn2);
        buttonpanel.add(btn3);
        GPApanel.add(GPAlabel);

        this.setLayout(new BorderLayout());
        // this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setMinimumSize(new Dimension(600, 550));

        JLabel[] labels = new JLabel[data.length-1];
        for (int i = 0; i < data.length-1; i++) {
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
        ModuleItem firstModule = new ModuleItem(1, studentID);
        ModuleItem[] moduleItem = new ModuleItem[firstModule.getNum()];
        moduleItem[0] = firstModule;
        // ItemPanel.setLayout(new GridLayout((firstModule.getNum() / 2) + 1, 2, 10,
        // 10));
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        ItemPanel.add(firstModule);
        for (int i = 1; i < firstModule.getNum(); i++) {
            moduleItem[i] = new ModuleItem(i + 1, studentID);
            ItemPanel.add(moduleItem[i]);
        }
        // ItemPanel.setSize(600, 550);
        add(ItemPanel, "Center");

    }
}