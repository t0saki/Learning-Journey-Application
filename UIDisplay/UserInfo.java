package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo() {
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("Name", "Li Hua");
        String[] data = userInfo.getRow(rowIndex);

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
