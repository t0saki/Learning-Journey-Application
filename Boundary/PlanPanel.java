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
public class PlanPanel extends BaseDisplay{
    String displayType = "Plan Panel";
    public PlanPanel(String studentID) {
        super("Plan Panel");
        contentPanel = new Plan(studentID);
    }
}
