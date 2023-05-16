package Boundary;

import Control.BaseHandler;
import Control.UserInfoHandler;
import Entity.AchievementItem;
import Entity.SkillItem;

import javax.swing.*;
import java.awt.*;

public class SkillInfo extends JPanel {
    public SkillInfo(String studentID){
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setLayout(new BorderLayout());

        JLabel[] labels = new JLabel[data.length-1];
        for (int i = 0; i < data.length-1; i++) {
            labels[i] = new JLabel(data[i]);
        }

        for (JLabel label : labels) {
            userPanel.add(label);
        }

        add(userPanel, "North");

        JPanel ItemPanel = new JPanel();
        BaseHandler baseHandler=new BaseHandler();
        baseHandler.open("Data/skills/"+studentID+".csv");
        int linecount=baseHandler.getLineCount();
        SkillItem[] skills = new SkillItem[linecount];
        // ItemPanel.setLayout(new GridLayout((firstModule.getNum() / 2) + 1, 2, 10,
        // 10));
        ItemPanel.setLayout(new BoxLayout(ItemPanel, BoxLayout.PAGE_AXIS));
        ItemPanel.setMinimumSize(new Dimension(600, 550));
        for (int i = 0; i < linecount; i++) {
            skills[i] = new SkillItem(studentID,i);
            JPanel skillpanel=new JPanel();
            skillpanel.setSize(this.getWidth(),200);
            skillpanel.setLayout(new GridLayout(1,1));
            skillpanel.add(skills[i]);
            ItemPanel.add(skillpanel);
        }
        add(ItemPanel, "Center");
        this.setVisible(true);

    }
}
