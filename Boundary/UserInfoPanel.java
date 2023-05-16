package Boundary;

import java.awt.*;

public class UserInfoPanel extends BaseDisplay {
    String displayType = "User Info";

    // Show a user's information
    public UserInfoPanel(String studentID) {
        super("User Info");
        contentPanel = new UserInfo(studentID);
        // Set background color
        setBackground(Color.white);
    }
}
