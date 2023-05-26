package Boundary;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *       the Course Schedule panel, used to display the course schedule of a
 *       student
 */

public class CourseSchedulePanel extends BaseDisplay {
    public CourseSchedulePanel(String studentID) {
        super("Course Schedule");
        contentPanel = new CourseScheduleInfo(studentID);
    }
}
