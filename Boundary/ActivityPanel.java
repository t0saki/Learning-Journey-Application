package Boundary;

import java.awt.*;

public class ActivityPanel extends BaseDisplay{
    String displayType = "activity Panel";
    public ActivityPanel(String studentID) {
        super("Activity Panel");
        contentPanel = new ActivityInfo(studentID);
        // Set background color
        setBackground(Color.blue);
    }
}
