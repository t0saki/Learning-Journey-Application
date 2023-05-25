package Boundary;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       side panel which creates the detailed panel
 */
public class CurriculumPanel extends BaseDisplay {
    String displayType = "activity Panel";

    public CurriculumPanel(String studentID) {
        super("Curriculum Panel");
        contentPanel = new CurriculumInfo(studentID);
    }
}
