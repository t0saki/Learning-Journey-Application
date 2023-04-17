package UIDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Base.PasswordHandler;
import FileHandler.BaseHandler;

public class Login {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    // Just show a simple login screen
    public Login() {
        // Create a new JFrame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        /// panel 1
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        panel1.add(usernameLabel);
        panel1.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel1.add(passwordLabel);
        panel1.add(passwordField);

        /// panel 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2, 5, 5));

        loginButton = new JButton("Login");
        panel2.add(loginButton);
        loginButton.addActionListener(e -> {
            BaseHandler baseHandler = new BaseHandler();
            baseHandler.open("Data\\UserInfo.csv");

            int rowId = baseHandler.getFirstRowIndexByHeaderAndVal("Username", usernameField.getText());
            if (rowId != -1) {
                // right password
                PasswordHandler ph = new PasswordHandler();
                if (ph.checkPassword(passwordField.getText(), baseHandler.getElement("Password", rowId))) {
                    String studentID = baseHandler.getElement("StudentId", rowId);
                    System.out.println("Student ID: " + studentID);
                    frame.dispose();
                    new Menu(studentID);
                }
                // bad password
                else {
                    JOptionPane.showMessageDialog(frame, "BAD PASSWORD", "Warning", JOptionPane.PLAIN_MESSAGE);
                    System.out.println("BAD PASSWORD");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "USER NAME NOT FOUND", "Warning", JOptionPane.PLAIN_MESSAGE);
                System.out.print("USER NAME NOT FOUND");
            }

            // Close the login screen
        });

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();
                frame.dispose();
            }
        });
        panel2.add(registerButton);

        frame.getContentPane().add(panel1, BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
