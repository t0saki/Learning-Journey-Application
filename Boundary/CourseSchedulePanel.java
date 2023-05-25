package Boundary;

public class CourseSchedulePanel extends BaseDisplay {
    public CourseSchedulePanel(String studentID) {
        super("Course Schedule");
        contentPanel = new CourseSchedule(studentID);
    }
}
