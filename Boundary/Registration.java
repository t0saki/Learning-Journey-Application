package Boundary;

import Control.PasswordHandler;
import Entity.Student;
import Control.BaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Ruitian Yang
 * @author Yurong He
 * @date 2023/05/25
 * the registration panel
 */
public class Registration extends JFrame implements ActionListener {
    private final JTextField usernameField;
    private final JTextField studentIdField;
    private final JComboBox<String> majorField;
    private final JTextField gradeField;

    private final JPasswordField passwordField;
    private final JButton registerButton;

    // Just show a simple login screen
    public Registration() {
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create a new JPanel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new GridBagLayout());

        // Create a new GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Register");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel studentIDLabel = new JLabel("Student ID:");
        studentIdField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        registerButton = new MyButton("Register");
        registerButton.addActionListener(this);

        // Add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(titleLabel, constraints);

        // constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(usernameLabel, constraints);

        // constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(passwordLabel, constraints);

        // constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(studentIDLabel, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weightx = 1.0;
        panel.add(usernameField, constraints);

        constraints.gridy = 2;
        panel.add(passwordField, constraints);

        constraints.gridy = 3;
        panel.add(studentIdField, constraints);

        // add major and grade
        JLabel majorLabel = new JLabel("Major:");
        majorField = new JComboBox<>(new String[]{"Telecommunications Engineering with Management", "E‐Commerce Engineering with Law", "Internet of Things Engineering", "Electronic Information Engineering", "Intelligent Science and Technology"});
        JLabel gradeLabel = new JLabel("Grade:");
        gradeField = new JTextField(20);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(majorLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(majorField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(gradeLabel, constraints);
        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(gradeField, constraints);

        // add register button
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.weightx = 0.0;
        panel.add(registerButton, constraints);

        add(panel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String studentID = studentIdField.getText();

            // if empty
            if (username.equals("") || password.equals("") || studentID.equals("") || majorField.getSelectedItem().toString().equals("") || gradeField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in all the fields!", "Warning",
                        JOptionPane.PLAIN_MESSAGE);
                return;
            }

            // check student id and grade is number
            try {
                Integer.parseInt(studentID);
                Integer.parseInt(gradeField.getText());
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Student ID and grade must be numbers!", "Warning",
                        JOptionPane.PLAIN_MESSAGE);
                return;
            }

            //
            // ...
//            Student newStudent = new Student(studentIdField.getText(), usernameField.getText(),
//                    PasswordHandler.hashPassword(String.valueOf(passwordField.getPassword())));

            Student newStudent = new Student(studentIdField.getText(), usernameField.getText(),
                    PasswordHandler.hashPassword(String.valueOf(passwordField.getPassword())), majorField.getSelectedItem().toString(), gradeField.getText());

            System.out.println(newStudent);
            BaseHandler baseHandler = new BaseHandler();
            baseHandler.open("Data\\UserInfo.csv");

            // same student id exists in the database
            if (baseHandler.getFirstRowIndexByHeaderAndVal("StudentId", newStudent.getStudentId()) != -1) {
                System.out.println("Registration failed: the same student id is found in the database!");
                JOptionPane.showMessageDialog(this,
                        "Registration failed: the same student id is found in the database!", "Warning",
                        JOptionPane.PLAIN_MESSAGE);
            }
            // registration succeeds
            else {
                baseHandler.append(newStudent.toCSVRow());

                // TODO: enable this after we can actually add data to these files
                baseHandler.create("Achievement", studentID);
                baseHandler.create("Curriculum", studentID);
                baseHandler.create("Portfolios", studentID);
                baseHandler.create("Roles", studentID);
//                baseHandler.create("Schedule", studentID);
                baseHandler.create("Skills", studentID);

                JOptionPane.showMessageDialog(this, "Registration succeeded!");
                dispose();
                new Login();
            }
            baseHandler.close();
        }
    }
}
