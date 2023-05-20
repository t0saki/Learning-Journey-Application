package Boundary;

import Control.BaseHandler;
import Control.Operate;
import Control.UserInfoHandler;
import Entity.SkillItem;
import Entity.SkillItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkillInfo extends JPanel {
    String header="Skills";
    String studentID;

    public String getHeader() {
        return header;
    }

    public SkillInfo(String studentID){
        this.studentID=studentID;
        refresh(this.studentID);
    }
    public void refresh(String studentID){
        this.removeAll();
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel operatepanel=new JPanel();
        operatepanel.setLayout(new GridLayout(1,3,5,0));
        JPanel titlepanel=new JPanel();
        titlepanel.setLayout(new GridLayout(2,1,5,5));

        JButton add=new JButton("Add");
        JButton delete=new JButton("Delete");
        JButton change=new JButton("Change");
        operatepanel.add(add);
        operatepanel.add(delete);
        operatepanel.add(change);

        this.setLayout(new BorderLayout());

        JLabel[] labels = new JLabel[data.length-1];
        for (int i = 0; i < data.length-1; i++) {
            labels[i] = new JLabel(data[i]);
        }

        for (JLabel label : labels) {
            userPanel.add(label);
        }

        titlepanel.add(userPanel);
        titlepanel.add(operatepanel);
        this.add(titlepanel, "North");

        JPanel ItemPanel = new JPanel();
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/Skills/" +studentID+".csv");
        int linecount=baseHandler.getLineCount();
        SkillItem[] Skills = new SkillItem[linecount];
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        for (int i = 0; i < linecount; i++) {
            Skills[i] = new SkillItem(studentID,i);
            JPanel Skillpanel=new JPanel();
            Skillpanel.setSize(this.getWidth(),200);
            Skillpanel.setLayout(new GridLayout(1,1));
            Skillpanel.add(Skills[i]);
            ItemPanel.add(Skillpanel);
        }
        this.add(ItemPanel, "Center");
        this.setVisible(true);
        baseHandler.close();
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillInfo.addItem additem=new SkillInfo.addItem();
                refresh(studentID);

            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillInfo.changeItem changeItem=new SkillInfo.changeItem();
                refresh(studentID);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillInfo.deleteItem deleteItem=new SkillInfo.deleteItem();
                refresh(studentID);
            }
        });

    }
    private class addItem{
        JFrame frame;
        public addItem(){
            String header=getHeader();
            this.frame=new JFrame();
            JPanel titlepanel=new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(3,1,5,5));
            frame.setTitle("Add Item");
            frame.setSize(300,200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title=new JLabel("Add Item");
            JTextField itemfield=new JTextField();
            JButton btn=new JButton("Confirm");

            titlepanel.add(title);
            frame.getContentPane().add(titlepanel);
            frame.getContentPane().add(itemfield);
            frame.getContentPane().add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String val=itemfield.getText();
                    if(Operate.addItem(studentID,header,val)==0){
                        JOptionPane.showMessageDialog(frame,"Add Success","Notice",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                    }
                }
            });
        }

    }
    private class deleteItem{
        JFrame frame;
        public deleteItem(){
            String header=getHeader();
            this.frame=new JFrame();
            JPanel titlepanel=new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(3,1,5,5));
            frame.setTitle("delete Item");
            frame.setSize(300,200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title=new JLabel("delete Item");
            JTextField itemfield=new JTextField();
            JButton btn=new JButton("Confirm");

            titlepanel.add(title);
            frame.getContentPane().add(titlepanel);
            frame.getContentPane().add(itemfield);
            frame.getContentPane().add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String val=itemfield.getText();
                    if(Operate.deleteItem(studentID,header,val)==0){
                        JOptionPane.showMessageDialog(frame,"delete Success","Notice",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                    }else{
                        JOptionPane.showMessageDialog(frame,"Item not exist!","Warning",JOptionPane.PLAIN_MESSAGE);
                        SkillInfo.deleteItem deleteItem=new SkillInfo.deleteItem();
                        frame.dispose();
                        refresh(studentID);
                    }
                }
            });
        }

    }
    private class changeItem{
        JFrame frame;
        public changeItem(){
            String header=getHeader();
            this.frame=new JFrame();
            JPanel titlepanel=new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(4,1,5,5));
            frame.setTitle("change Item");
            frame.setSize(500,200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title=new JLabel("change Item");
            JTextField itemfield=new JTextField();
            JLabel targetlabel=new JLabel("Item you want to change");
            JTextField targetfield=new JTextField();
            JLabel itemlabel=new JLabel("new item");
            JButton btn=new JButton("Confirm");

            JPanel panel1=new JPanel();
            JPanel panel2=new JPanel();
            panel1.setLayout(new GridLayout(1,2,5,5));
            panel2.setLayout(new GridLayout(1,2,5,5));
            panel1.add(targetlabel);
            panel1.add(targetfield);
            panel2.add(itemlabel);
            panel2.add(itemfield);

            titlepanel.add(title);
            frame.getContentPane().add(titlepanel);
            frame.getContentPane().add(panel1);
            frame.getContentPane().add(panel2);
            frame.getContentPane().add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String val=itemfield.getText();
                    if(Operate.changeItem(studentID,header,targetfield.getText(),itemfield.getText())==0){
                        JOptionPane.showMessageDialog(frame,"change Success","Notice",JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                    }else{
                        JOptionPane.showMessageDialog(frame,"Item not exist!","Warning",JOptionPane.PLAIN_MESSAGE);
                        SkillInfo.changeItem changeItem=new SkillInfo.changeItem();
                        frame.dispose();
                        refresh(studentID);
                    }
                }
            });
        }

    }
}
