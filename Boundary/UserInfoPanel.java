package Boundary;

public class UserInfoPanel extends BaseDisplay {
    String displayType = "User Info";

    // Show a user's information
    public UserInfoPanel(String studentID,HistogramPanel histogramPanel) {
        super("Course Mark Info");
        contentPanel = new UserInfo(studentID, histogramPanel);
    }
}
