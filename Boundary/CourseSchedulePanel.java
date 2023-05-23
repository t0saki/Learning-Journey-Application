package Boundary;

import java.awt.*;

public class CourseSchedulePanel extends BaseDisplay {
    public CourseSchedulePanel(String studentID) {
        super("Course Schedule");
        contentPanel = new CourseSchedule(studentID);
        // Set background color
        setBackground(Color.white);
    }
}
