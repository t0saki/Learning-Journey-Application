package Boundary;

import Control.BaseHandler;
import Control.Operate;
import Control.UserInfoHandler;
import Entity.RoleItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       the detailed panel showcasing user's roles
 */
public class RoleInfo extends JPanel {
    String header = "Roles";
    String studentID;

    public String getHeader() {
        return header;
    }

    public RoleInfo(String studentID) {
        setPreferredSize(new Dimension(680, 900));
        this.studentID = studentID;
        refresh(this.studentID);
    }

    /**
     * @param studentID
     *                  refresh every time when there are changes
     *                  to information, regenerate the panel.
     */
    public void refresh(String studentID) {
        this.removeAll();
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel operatepanel = new JPanel();
        operatepanel.setLayout(new GridLayout(1, 3, 5, 0));
        JPanel titlepanel = new JPanel();
        titlepanel.setLayout(new GridLayout(2, 1, 5, 5));

        JButton add = new JButton("Add");
        JButton delete = new JButton("Delete");
        JButton change = new JButton("Change");
        operatepanel.add(add);
        operatepanel.add(delete);
        operatepanel.add(change);

        this.setLayout(new BorderLayout());

        String[] str = new String[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            if (i >= 2) {
                str[i] = data[i + 1];
            } else {
                str[i] = data[i];
            }
        }

        // Show on this JPanel
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><center>Name: " + str[0] + "<br>");
        stringBuilder.append("Student ID:" + str[1] + "<br>");
        stringBuilder.append("Major: " + str[2] + "<br>");
        stringBuilder.append("Entrance year: " + str[3] + "</center></html>");

        JLabel titlelable = new JLabel(stringBuilder.toString());
        titlelable.setFont(new Font("", Font.PLAIN, 30));
        userPanel.add(titlelable);

        titlepanel.add(userPanel);
        titlepanel.add(operatepanel);
        this.add(titlepanel, "North");

        JPanel ItemPanel = new JPanel();
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Roles/" + studentID + ".csv");
        int linecount = baseHandler.getLineCount();
        RoleItem[] Roles = new RoleItem[linecount];
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        for (int i = 0; i < linecount; i++) {
            Roles[i] = new RoleItem(studentID, i);
            JPanel Rolespanel = new JPanel();
            Rolespanel.setSize(this.getWidth(), 200);
            Rolespanel.setLayout(new GridLayout(1, 1));
            Rolespanel.add(Roles[i]);
            ItemPanel.add(Rolespanel);
        }
        this.add(ItemPanel, "Center");
        this.setVisible(true);
        baseHandler.close();
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleInfo.addItem additem = new RoleInfo.addItem();
                refresh(studentID);

            }
        });
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleInfo.changeItem changeItem = new RoleInfo.changeItem();
                refresh(studentID);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoleInfo.deleteItem deleteItem = new RoleInfo.deleteItem();
                refresh(studentID);
            }
        });

    }

    /**
     * @author XiangzheKong
     * @date 2023/05/25
     *       create a frame to help with
     *       the addition to the panel
     */
    private class addItem {
        JFrame frame;

        public addItem() {
            String header = getHeader();
            this.frame = new JFrame();
            JPanel titlepanel = new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(3, 1, 5, 5));
            frame.setTitle("Add Item");
            frame.setSize(300, 200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title = new JLabel("Add Item");
            JTextField itemfield = new JTextField();
            JButton btn = new JButton("Confirm");

            titlepanel.add(title);
            frame.getContentPane().add(titlepanel);
            frame.getContentPane().add(itemfield);
            frame.getContentPane().add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String val = itemfield.getText();
                    if (Operate.addItem(studentID, header, val) == 0) {
                        JOptionPane.showMessageDialog(frame, "Add Success", "Notice", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                        revalidate();
                    }
                }
            });
        }

    }

    /**
     * @author XiangzheKong
     * @date 2023/05/25
     *       create a frame to help with
     *       the deleting to the panel
     */
    private class deleteItem {
        JFrame frame;

        public deleteItem() {
            String header = getHeader();
            this.frame = new JFrame();
            JPanel titlepanel = new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(3, 1, 5, 5));
            frame.setTitle("delete Item");
            frame.setSize(300, 200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title = new JLabel("delete Item");
            JTextField itemfield = new JTextField();
            JButton btn = new JButton("Confirm");

            titlepanel.add(title);
            frame.getContentPane().add(titlepanel);
            frame.getContentPane().add(itemfield);
            frame.getContentPane().add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String val = itemfield.getText();
                    if (Operate.deleteItem(studentID, header, val) == 0) {
                        JOptionPane.showMessageDialog(frame, "delete Success", "Notice", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                        revalidate();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Item not exist!", "Warning", JOptionPane.PLAIN_MESSAGE);
                        RoleInfo.deleteItem deleteItem = new RoleInfo.deleteItem();
                        frame.dispose();
                        refresh(studentID);
                        revalidate();
                    }
                }
            });
        }

    }

    /**
     * @author XiangzheKong
     * @date 2023/05/25
     *       create a frame to help with
     *       the changing to the panel
     */
    private class changeItem {
        JFrame frame;

        public changeItem() {
            String header = getHeader();
            this.frame = new JFrame();
            JPanel titlepanel = new JPanel();
            titlepanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            frame.getContentPane().setLayout(new GridLayout(4, 1, 5, 5));
            frame.setTitle("change Item");
            frame.setSize(500, 200);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

            JLabel title = new JLabel("change Item");
            JTextField itemfield = new JTextField();
            JLabel targetlabel = new JLabel("Item you want to change");
            JTextField targetfield = new JTextField();
            JLabel itemlabel = new JLabel("new item");
            JButton btn = new JButton("Confirm");

            JPanel panel1 = new JPanel();
            JPanel panel2 = new JPanel();
            panel1.setLayout(new GridLayout(1, 2, 5, 5));
            panel2.setLayout(new GridLayout(1, 2, 5, 5));
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
                    String val = itemfield.getText();
                    if (Operate.changeItem(studentID, header, targetfield.getText(), itemfield.getText()) == 0) {
                        JOptionPane.showMessageDialog(frame, "change Success", "Notice", JOptionPane.PLAIN_MESSAGE);
                        frame.dispose();
                        refresh(studentID);
                        revalidate();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Item not exist!", "Warning", JOptionPane.PLAIN_MESSAGE);
                        RoleInfo.changeItem changeItem = new RoleInfo.changeItem();
                        frame.dispose();
                        refresh(studentID);
                        revalidate();
                    }
                }
            });
        }

    }
}
