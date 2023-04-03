package UIDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registration {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton registerButton;
    // Just show a simple login screen
    public Registration() {
        // Create a new JFrame
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JPanel panel1=new JPanel();
        panel1.setLayout(new GridLayout(3,2,5,5));
        // Add the UI components to the panel
        JLabel usernameLabel = new JLabel("Username:");
        panel1.add(usernameLabel);

        usernameField = new JTextField();
        panel1.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel1.add(passwordLabel);

        passwordField = new JPasswordField();
        panel1.add(passwordField);

        // Add a button to the JFrame
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(1,2,5,5));
//        frame.add(button);
        registerButton =new JButton("register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new Registration();
//                frame.dispose();
            }
        });
        panel2.add(registerButton);
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
