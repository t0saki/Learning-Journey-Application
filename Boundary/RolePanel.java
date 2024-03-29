package Boundary;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       side panel which creates the detailed panel
 */
public class RolePanel extends BaseDisplay {
    String displayType = "Role Panel";

    public RolePanel(String studentID) {
        super("Role Panel");
        contentPanel = new RoleInfo(studentID);
    }
}
