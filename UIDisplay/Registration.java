package UIDisplay;

import javax.swing.*;

import Base.Student;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {
    private JTextField usernameField;
    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JButton registerButton;

    // Just show a simple login screen
    public Registration() {
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

        JLabel studentIdLabel = new JLabel("Student Id:");
        studentIdField = new JTextField();
        panel1.add(studentIdLabel);
        panel1.add(studentIdField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        panel1.add(passwordLabel);
        panel1.add(passwordField);

        /// panel 2
        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 2, 5, 5));

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student newStudent = new Student(studentIdField.getText(), usernameField.getText(),
                        String.valueOf(passwordField.getPassword()));

                System.out.println(newStudent);
                // new Registration();
                // frame.dispose();
            }
        });
        panel2.add(registerButton);

        frame.getContentPane().add(panel1, BorderLayout.NORTH);
        frame.getContentPane().add(panel2, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
