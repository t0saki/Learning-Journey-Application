package UIDisplay;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;

    // Just show a simple login screen
    public Login() {
        // Create a new JFrame
        JFrame frame = new JFrame("Login");
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
        loginButton = new JButton("Login");
        JPanel panel2=new JPanel();
        panel2.setLayout(new GridLayout(1,2,5,5));
        panel2.add(loginButton);
//        frame.add(button);
        loginButton.addActionListener(e -> {
            // When the button is clicked, open the menu
            new Menu();
            // Close the login screen
            frame.dispose();
        });
        registerButton =new JButton("register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();
                frame.dispose();
            }
        });
        panel2.add(registerButton);
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        frame.getContentPane().add(panel2,BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
