package Boundary;

import java.awt.*;

public class PlanPanel extends BaseDisplay{
    String displayType = "Plan Panel";
    public PlanPanel(String studentID) {
        super("Plan Panel");
        contentPanel = new Plan(studentID);
        // Set background color
        setBackground(Color.white);
    }
}
