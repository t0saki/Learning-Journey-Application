package Boundary;

import java.awt.*;

public class skillPanel extends BaseDisplay{
    String displayType = "skill Panel";
    public skillPanel(String studentID) {
        super("Skill Panel");
        contentPanel = new SkillInfo(studentID);
        // Set background color
        setBackground(Color.yellow);
    }
}
