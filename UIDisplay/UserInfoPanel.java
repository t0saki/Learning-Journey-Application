package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;
import java.awt.*;

public class UserInfoPanel extends BaseDisplay {
    String displayType = "User Info";

    // Show a user's information
    public UserInfoPanel() {
        super("User Info");
        contentPanel = new UserInfo();
    }
}
