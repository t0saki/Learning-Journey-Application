import UIDisplay.Login;

import javax.swing.*;

public class LearningJourneyApp {
    public static void main(String[] args) {
       // JFrame style to match the OS
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the user interface
        new Login();
    }
}
