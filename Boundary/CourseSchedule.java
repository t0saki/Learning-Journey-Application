package Boundary;

import Control.BaseHandler;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CourseSchedule extends JPanel {

    private static final String[] DAYS_OF_WEEK = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final String[] TIME_SLOTS = {"Morning", "Late Morning", "Noon", "Afternoon", "Late Afternoon", "Evening"};

    private String studentID;

    public CourseSchedule(String studentID) {
        setPreferredSize(new Dimension(600, 700));
        this.studentID = studentID;
        refresh();
    }

    public void refresh() {
        this.removeAll();
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
        // JPanel tempPanel = new JPanel();
        // tempPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // add(tempPanel);
        for (int i = 0; i < DAYS_OF_WEEK.length; i++) {
            add(dayLabelArray[i]);
        }

        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Schedule/" + studentID + ".csv");
        String[][] schedule = baseHandler.getFileData();
        // Course,Day,Time,Location,Professor
        baseHandler.close();
        HashMap<String, Integer> scheduleMap = new HashMap<>();
        for (int i = 0; i < schedule.length; i++) {
            scheduleMap.put(schedule[i][1] + " " + schedule[i][2], i);
        }

        // Add time labels to the left column and empty cells to the rest
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            add(timeLabelArray[i]);
            for (int j = 0; j < DAYS_OF_WEEK.length; j++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                cellPanel.setLayout(new GridLayout(3, 1));
                if (scheduleMap.containsKey(DAYS_OF_WEEK[j] + " " + TIME_SLOTS[i])) {
                    // Bigger course name
                    JLabel courseLabel = new JLabel(schedule[scheduleMap.get(DAYS_OF_WEEK[j] + " " + TIME_SLOTS[i])][0], SwingConstants.CENTER);
                    JLabel locationLabel = new JLabel(schedule[scheduleMap.get(DAYS_OF_WEEK[j] + " " + TIME_SLOTS[i])][3], SwingConstants.CENTER);
                    JLabel professorLabel = new JLabel(schedule[scheduleMap.get(DAYS_OF_WEEK[j] + " " + TIME_SLOTS[i])][4], SwingConstants.CENTER);


                    courseLabel.setFont(courseLabel.getFont().deriveFont(Font.BOLD, 20f));
                    // Smaller other info
                    locationLabel.setFont(locationLabel.getFont().deriveFont(Font.PLAIN, 14f));
                    professorLabel.setFont(professorLabel.getFont().deriveFont(Font.PLAIN, 14f));

                    cellPanel.add(courseLabel);
                    cellPanel.add(locationLabel);
                    cellPanel.add(professorLabel);

                    cellPanel.setBackground(Color.GREEN);
                }
                add(cellPanel);
            }
        }
    }
}