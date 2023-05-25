package Boundary;

import java.awt.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 * side panel which creates the detailed panel
 */
public class ActivityPanel extends BaseDisplay{
    String displayType = "activity Panel";
    public ActivityPanel(String studentID) {
        super("Activity Panel");
        contentPanel = new ActivityInfo(studentID);
    }
}
