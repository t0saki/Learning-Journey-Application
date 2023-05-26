package Boundary;

import Control.FontManager;

import javax.swing.*;

public class CourseScheduleInfo extends JPanel {
    CourseSchedule courseSchedule;

    CourseScheduleInfo(String studentID) {
        courseSchedule = new CourseSchedule(studentID);

        // Set outer border
        setBorder(BorderFactory.createEmptyBorder(50,50 , 50, 50));

//        histogram = new Histogram(data, 10);

        // Header
        JPanel title = new JPanel();
        JLabel titlelable = new JLabel("<html><center>Course Schedule</center></html>");
        titlelable.setFont(FontManager.getIBSCartooning(110));
        titlelable.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        // userPanel.add(titlelable);
        // titlelable.setFont(new Font("", Font.PLAIN, 30));
        title.add(titlelable);

        // Add components
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(title);

        // Add histogram
        add(courseSchedule);

    }
}
