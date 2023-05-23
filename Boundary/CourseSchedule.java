package Boundary;

import javax.swing.*;
import java.awt.*;

public class CourseSchedule extends JPanel {

    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] TIME_SLOTS = {"Morning", "Noon", "Afternoon", "Evening"};

    public CourseSchedule() {
        setLayout(new GridLayout(TIME_SLOTS.length + 1, DAYS_OF_WEEK.length + 1));

        // Create empty top-left cell
        add(new JPanel());
        JLabel[] timeLabelArray = new JLabel[TIME_SLOTS.length];
        JLabel[] dayLabelArray = new JLabel[DAYS_OF_WEEK.length];

        // Create header labels for time slots
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            timeLabelArray[i] = new JLabel(TIME_SLOTS[i], SwingConstants.CENTER);
            timeLabelArray[i].setFont(timeLabelArray[i].getFont().deriveFont(Font.BOLD));
            add(timeLabelArray[i]);
        }

        // Create header labels for days of the week
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            dayLabelArray[i] = new JLabel(DAYS_OF_WEEK[i], SwingConstants.CENTER);
            dayLabelArray[i].setFont(dayLabelArray[i].getFont().deriveFont(Font.BOLD));
            add(dayLabelArray[i]);
        }

        // Add day labels to the top row
//        JPanel tempPanel = new JPanel();
//        tempPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        add(tempPanel);
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            add(dayLabelArray[i]);
        }

        // Add time labels to the left column and empty cells to the rest
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            add(timeLabelArray[i]);
            for (int j = 0; j < DAYS_OF_WEEK.length; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                add(cellPanel);
            }
        }
    }
}