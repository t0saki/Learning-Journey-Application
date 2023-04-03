package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo() {
        UserInfoHandler userInfo = new UserInfoHandler("Data\\UserInfo.csv");
        userInfo.open("Li Hua");
        String[] data = userInfo.getData();


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
