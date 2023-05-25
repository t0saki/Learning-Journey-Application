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
public class AchievementPanel extends BaseDisplay {
    String displayType = "Achievement Panel";

    public AchievementPanel(String studentID) {
        super("Achievement Panel");
        contentPanel = new Achievement(studentID);
    }
}
