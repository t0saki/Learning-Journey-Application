package Boundary;

import Control.Operate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addItemWin {
    String studentID;
    String header;
    JFrame frame;
    int index;

    public addItemWin(String ID, String header) throws HeadlessException {
        this.studentID = ID;
        this.header = header;
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

                }
            }
        });
    }
}
