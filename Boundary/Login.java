package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.PasswordHandler;
import Control.BaseHandler;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    // Just show a simple login screen
    public Login() {
        // Create a new JFrame
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        panel.add(usernameField, constraints);

        constraints.gridy = 2;
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weightx = 0.0;
        panel.add(loginButton, constraints);

        constraints.gridy = 4;
        panel.add(registerButton, constraints);

        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null); // 居中显示

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

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
                checkPassword = true; // TODO: delete this line
                if (checkPassword) {
                    String studentID = baseHandler.getElement("StudentId", rowId);
                    System.out.println("Student ID: " + studentID);
                    JOptionPane.showMessageDialog(this, "Login successful!");
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

            // 登录成功后的操作

            // dispose(); // 关闭登录窗口
        } else if (e.getSource() == registerButton) {
            // 处理注册按钮点击事件
            JOptionPane.showMessageDialog(this, "Redirecting to registration page...");
            new Registration();
            dispose();
        }
    }
}
