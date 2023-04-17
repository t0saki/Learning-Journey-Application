package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;
import java.awt.*;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo(String studentID) {
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setLayout(new BorderLayout());
        // this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setMinimumSize(new Dimension(600, 550));

        JLabel[] labels = new JLabel[data.length];
        for (int i = 0; i < data.length; i++) {
            labels[i] = new JLabel(data[i]);
        }

        // Show on this JPanel
        for (JLabel label : labels) {
            userPanel.add(label);
        }
        add(userPanel, "North");

        JPanel ItemPanel = new JPanel();
        ModuleItem firstModule = new ModuleItem(1, studentID);
        ModuleItem[] moduleItem = new ModuleItem[firstModule.getNum()];
        moduleItem[0] = firstModule;
        // ItemPanel.setLayout(new GridLayout((firstModule.getNum() / 2) + 1, 2, 10,
        // 10));
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        ItemPanel.add(firstModule);
        for (int i = 1; i < firstModule.getNum(); i++) {
            moduleItem[i] = new ModuleItem(i + 1, studentID);
            ItemPanel.add(moduleItem[i]);
        }
        // ItemPanel.setSize(600, 550);
        add(ItemPanel, "Center");

    }
}