package Boundary;

import java.awt.*;

public class AchievementPanel extends BaseDisplay {
    String displayType = "Achievement Panel";
    public AchievementPanel(String studentID) {
        super("Achievement Panel");
        contentPanel = new Achievement(studentID);
        // Set background color
        setBackground(Color.white);
    }
}
