package Boundary;

<<<<<<< Updated upstream
=======
import java.awt.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 * side panel which creates the detailed panel
 */
>>>>>>> Stashed changes
public class skillPanel extends BaseDisplay{
    String displayType = "skill Panel";
    public skillPanel(String studentID) {
        super("Skill Panel");
        contentPanel = new SkillInfo(studentID);
    }
}
