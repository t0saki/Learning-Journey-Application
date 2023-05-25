package Boundary;

public class AchievementPanel extends BaseDisplay {
    String displayType = "Achievement Panel";

    public AchievementPanel(String studentID) {
        super("Achievement Panel");
        contentPanel = new Achievement(studentID);
    }
}
