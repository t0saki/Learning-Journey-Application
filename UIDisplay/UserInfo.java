package UIDisplay;

import javax.swing.*;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo() {
        // Just random data
        String[] data = new String[]{
                "Name: John Doe",
                "Email: johndoe@qmul.ac.uk"
        };

        JLabel[] labels = new JLabel[data.length];
        for (int i = 0; i < data.length; i++) {
            labels[i] = new JLabel(data[i]);
        }

        // Show on this JPanel
        for (JLabel label : labels) {
            add(label);
        }
    }
}
