package Boundary;

public class PortfoliosPanel extends BaseDisplay{
    String displayType = "Portfolios Panel";
    public PortfoliosPanel(String studentID) {
        super("Portfolios Panel");
        contentPanel = new PortfoliosInfo(studentID);
    }
}
