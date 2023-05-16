package Boundary;

import java.awt.*;

public class RolePanel extends BaseDisplay{
    String displayType = "Role Panel";
    public RolePanel(String studentID) {
        super("Role Panel");
        contentPanel = new RoleInfo(studentID);
        // Set background color
        setBackground(Color.orange);
    }
}
