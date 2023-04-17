package UIDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            
            // if (baseHandler.getFirstRowIndexByHeaderAndVal("StudentId", newStudent.getStudentId()) != -1) {

            // }

            // When the button is clicked, open the menu
            new Menu();
            // Close the login screen
            frame.dispose();
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
