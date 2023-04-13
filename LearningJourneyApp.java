import UIDisplay.Login;
import UIDisplay.UserInfoPanel;

import javax.swing.*;

public class LearningJourneyApp {
    public static void main(String[] args) {
        // JFrame style to match the OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // debug
        // show UserInfo in standalone JFrame
        JFrame frame = new JFrame("UserInfo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.getContentPane().add(new UserInfoPanel().getContentPanel());


        // Initialize the user interface
        new Login();
    }
}
