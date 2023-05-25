package Boundary;

import java.awt.*;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       side panel which creates the detailed panel
 */
public class PortfoliosPanel extends BaseDisplay {
    String displayType = "Portfolios Panel";

    public PortfoliosPanel(String studentID) {
        super("Portfolios Panel");
        contentPanel = new PortfoliosInfo(studentID);
    }
}
