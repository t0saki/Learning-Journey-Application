package Boundary;

import Control.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author XiangzheKong
 * @date 2023/05/25
 *       the detailed panel showcasing user's individual plan
 */
public class Plan extends JPanel {
    String header = "Plans";
    String studentID;

    public String getHeader() {
        return header;
    }

    public Plan(String studentID) {
        setPreferredSize(new Dimension(680, 900));
        this.studentID = studentID;
        refresh(this.studentID);
    }

    public void refresh(String studentID) {
        this.removeAll();
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);

        //Title
        JPanel userpanel=new JPanel();
        userpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel titlelable = new JLabel("<html><center>Personal Plan</center></html>");
        titlelable.setFont(FontManager.getIBSCartooning(100));
        titlelable.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        userpanel.add(titlelable);

        // User's name
        JPanel namepanel = new JPanel();
        namepanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel name = new JLabel("Name: " + userInfo.getElement("Username", rowIndex));
        name.setFont(new Font("",Font.PLAIN,30));
        name.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        namepanel.add(name);

        // User's GPA
        JPanel GPApanel = new JPanel();
        GPApanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel GPALabel = new JLabel("GPA(Standard calculation method): " + Operate.GPAhandler(studentID, 1));
        GPALabel.setFont(new Font("",Font.PLAIN,30));
        GPApanel.add(GPALabel);

        // User's highest mark course
        JPanel highestCourse = new JPanel();
        highestCourse.setLayout(new FlowLayout(FlowLayout.LEFT));
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<String, Integer> map = Operate.getHighestMark(studentID);
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            stringBuilder.append(entry.getKey() + " ");
        }
        JLabel coures = new JLabel("Highest Scoring Subject: " + stringBuilder.toString());
        coures.setFont(new Font("",Font.PLAIN,30));
        highestCourse.add(coures);

        // Display number of Achievements
        JPanel achievementpanel = new JPanel();
        achievementpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        BaseHandler baseHandler = new BaseHandler();
        baseHandler.open("Data/Achievements/" + studentID + ".csv");
        int num = baseHandler.getLineCount();
        JLabel achievementnum = new JLabel("Number of Achievements: " + num);
        achievementnum.setFont(new Font("",Font.PLAIN,30));
        achievementpanel.add(achievementnum);
        baseHandler.close();

        // Display number of Activities
        JPanel activitypanel = new JPanel();
        activitypanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseHandler.open("Data/Curriculum/" + studentID + ".csv");
        num = baseHandler.getLineCount();
        JLabel activitynum = new JLabel("Number of Curriculum: " + num);
        activitynum.setFont(new Font("",Font.PLAIN,30));
        activitypanel.add(activitynum);
        baseHandler.close();

        // Display number of Portfolios
        JPanel portfoliosPanel = new JPanel();
        portfoliosPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        baseHandler.open("Data/Portfolios/" + studentID + ".csv");
        num = baseHandler.getLineCount();
        JLabel Portfoliosnum = new JLabel("Number of Portfolios: " + num);
        Portfoliosnum.setFont(new Font("",Font.PLAIN,30));
        portfoliosPanel.add(Portfoliosnum);
        baseHandler.close();

        // Suggestion for user
        JPanel suggestionpanel = new JPanel();
        JTextArea textArea = new JTextArea();
        suggestionpanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        String suggestion = Operate.getComment(Operate.Analyse(studentID));
        JLabel suggestionlabel = new JLabel();
        // suggestionlabel.setText("Suggestion: "+suggestion);
        textArea.setText("Suggestion: " + suggestion);
        suggestionpanel.add(textArea);
        textArea.setColumns(65);
        textArea.setFont(new Font("",Font.PLAIN,20));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setOpaque(false);

        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(new GridLayout(7, 1, 5, 10));
        this.setLayout(new BorderLayout());

        mainPanel.add(namepanel);
        mainPanel.add(GPApanel);
        mainPanel.add(highestCourse);
        mainPanel.add(achievementpanel);
        mainPanel.add(activitypanel);
        mainPanel.add(portfoliosPanel);
        mainPanel.add(suggestionpanel);

        this.add(userpanel,BorderLayout.NORTH);
        this.add(mainPanel,BorderLayout.CENTER);


    }

}
