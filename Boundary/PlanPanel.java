package Boundary;

public class PlanPanel extends BaseDisplay{
    String displayType = "Plan Panel";
    public PlanPanel(String studentID) {
        super("Plan Panel");
        contentPanel = new Plan(studentID);
    }
}
