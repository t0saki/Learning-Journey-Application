package UIDisplay;

import javax.swing.*;

public class Login {
    // Just show a simple login screen
    public Login() {
        // Create a new JFrame
        JFrame frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Add a button to the JFrame
        JButton button = new JButton("Login");
        frame.add(button);
        button.addActionListener(e -> {
            // When the button is clicked, open the menu
            new Menu();
            // Close the login screen
            frame.dispose();
        });
    }
}
