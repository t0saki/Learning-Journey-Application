package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

import Control.*;

/**
 * @author Ruitian Yang
 * @author ZhengXiao Wu
 * @author YuRong He
 * @date 2023/05/25
 *       the login panel, containing login and register button
 */
public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MyButton loginButton;
    private MyButton registerButton;
    private Boolean fastDebug = true; // TODO: disable fastDebug when release

    // Just show a simple login screen

    /**
     * Constructor for the login panel
     */
    public Login() {
        // Create a new JFrame
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create a new JPanel
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(1000, 600));
        panel.setLayout(new GridBagLayout());

        // Create a new GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();

        // Create a new ImageBackgroundPanel
        JPanel picPanel = new ImageBackgroundPanel("Images\\Campus\\4.jpg", 600, 1);
        JPanel vertBlank1 = new JPanel();
        JPanel contentPanel = new JPanel(new GridBagLayout());
        JPanel vertBlank2 = new JPanel();

        // Create a new JLabel
        JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setForeground(GlobalColors.solidWhite);

        // Create a new JTextField
        JPanel b1 = new JPanel();
        JPanel b2 = new JPanel();
        JPanel b3 = new JPanel();
        JPanel b4 = new JPanel();
        JPanel b5 = new JPanel();

        // Create a new JPasswordField
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        usernameField.setMargin(new Insets(0, 10, 0, 0));
        passwordField.setMargin(new Insets(0, 10, 0, 0));

        // Create a new MyButton
        loginButton = new MyButton("Login");
        loginButton.setRadius(20);
        loginButton.addActionListener(this);

        // Create a new MyButton
        registerButton = new MyButton("Don't have an account? Sign up!");
        registerButton.setRadius(20);
        registerButton.addActionListener(this);

        // set fonts
        signInLabel.setFont(FontManager.getLatoBold(30));
        usernameField.setFont(FontManager.getLatoRegular(18));
        loginButton.setFont(FontManager.getLatoRegular(18));
        registerButton.setFont(FontManager.getLatoRegular(18));

        // set colors
        b1.setBackground(GlobalColors.lighterBlack);
        b2.setBackground(GlobalColors.lighterBlack);
        b3.setBackground(GlobalColors.lighterBlack);
        b4.setBackground(GlobalColors.lighterBlack);
        b5.setBackground(GlobalColors.lighterBlack);
        vertBlank1.setBackground(GlobalColors.lighterBlack);
        contentPanel.setBackground(GlobalColors.lighterBlack);
        vertBlank2.setBackground(GlobalColors.lighterBlack);

        // start filling
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        // picPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 1;
        panel.add(picPanel, gbc);

        // blank
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.075;
        gbc.weighty = 1;
        panel.add(vertBlank1, gbc);

        // content
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.2;
        contentPanel.add(signInLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.02;
        contentPanel.add(b1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.06;
        contentPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.02;
        contentPanel.add(b2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.06;
        contentPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.05;
        contentPanel.add(b3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.06;
        contentPanel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.05;
        contentPanel.add(b4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.06;
        contentPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 0.1;
        contentPanel.add(b5, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.35;
        gbc.weighty = 1;
        panel.add(contentPanel, gbc);

        // blank
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.075;
        gbc.weighty = 1;
        panel.add(vertBlank2, gbc);

        // We can also press enter to login
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        };

        // Add listener
        usernameField.addKeyListener(keyAdapter);
        passwordField.addKeyListener(keyAdapter);

        // For fast debug
        if (fastDebug) {
            usernameField.setText("John Doe");
            passwordField.setText("A");
        }

        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // 居中显示

        setVisible(true);
    }

    /**
     * @param e
     * @description: listen to the button
     * @return: void
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username;
            String password;

            username = usernameField.getText();
            password = new String(passwordField.getPassword());
            // check empty
            if (username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Username or password cannot be empty!", "Warning",
                        JOptionPane.PLAIN_MESSAGE);
                return;
            }

            // 在这里可以添加登录验证逻辑
            // ...
            BaseHandler baseHandler = new BaseHandler();
            baseHandler.open("Data\\UserInfo.csv");

            int rowId = baseHandler.getFirstRowIndexByHeaderAndVal("Username", usernameField.getText());
            if (rowId != -1) {
                // right password
                Boolean checkPassword = PasswordHandler.checkPassword(String.valueOf(passwordField.getPassword()),
                        baseHandler.getElement("Password", rowId));
                if (checkPassword) {
                    String studentID = baseHandler.getElement("StudentId", rowId);
                    System.out.println("Student ID: " + studentID);
                    // JOptionPane.showMessageDialog(this, "Login successful!");
                    dispose();

                    new Menu(studentID);
                }
                // bad password
                else {
                    JOptionPane.showMessageDialog(this, "BAD PASSWORD", "Warning", JOptionPane.PLAIN_MESSAGE);
                    System.out.println("BAD PASSWORD");
                }
            } else {
                JOptionPane.showMessageDialog(this, "USER NAME NOT FOUND", "Warning", JOptionPane.PLAIN_MESSAGE);
                System.out.print("USER NAME NOT FOUND");
            }
        } else if (e.getSource() == registerButton) {
            new Registration();
            dispose();
        }
    }
}
