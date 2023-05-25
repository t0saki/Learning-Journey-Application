package Boundary;

import javax.swing.*;
import java.awt.*;

import Control.*;
import java.io.*;
import java.util.*;

/**
 * @author RuitianYang
 * @date 2023/05/25
 *       the personal information panel
 */
public class PersonalInformationPanel extends JPanel {
    private String name;
    private String studentId;
    private String major;
    private String entranceYear;
    private ImageIcon photo;

    private String[] parseData(String data[]) {
        // Generate user's information
        String[] str = new String[data.length - 1];
        for (int i = 0; i < data.length - 1; i++) {
            if (i >= 2) {
                str[i] = data[i + 1];
            } else {
                str[i] = data[i];
            }
        }
        return str;
    }

    public PersonalInformationPanel(String studentID) {
        super(new BorderLayout());
        setBackground(GlobalColors.lighterBlack);

        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);

        String[] parsedData = parseData(data);
        this.name = parsedData[0];
        this.studentId = parsedData[1];
        this.major = parsedData[2];
        this.entranceYear = parsedData[3];

        File folder = new File("Images\\Avatars"); // 创建一个File对象，表示照片文件夹
        File[] files = folder.listFiles(); // 获取文件夹中的所有文件
        Random random = new Random(); // 创建一个Random对象，用来生成随机数
        int index = random.nextInt(files.length); // 生成一个随机数，作为文件数组的索引
        File file = files[index]; // 获取对应索引的文件
        photo = new ImageIcon(file.getPath()); // 创建一个ImageIcon对象，使用文件的路径

        JLabel photoLabel = new JLabel(photo);
        photoLabel.setPreferredSize(new Dimension(200, 200));
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setBorder(BorderFactory.createMatteBorder(20, 0, 0, 0, GlobalColors.lighterBlack));

        JLabel nameLabel = new JLabel(name);
        JLabel idLabel = new JLabel("BUPT #" + studentId);

        nameLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.selectedColor));
        idLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.selectedColor));

        nameLabel.setFont(FontManager.getLatoBold(20));
        idLabel.setFont(FontManager.getLatoBold(20));

        JPanel upperPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        upperPanel.add(photoLabel, gbc);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        upperPanel.add(nameLabel, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        upperPanel.add(idLabel, gbc);

        // set a red border for upperPanel
        upperPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        JLabel majorLabel = new JLabel(major);
        JLabel yearLabel = new JLabel(entranceYear);

        majorLabel.setFont(FontManager.getLatoRegular(14));
        yearLabel.setFont(FontManager.getLatoRegular(14));

        JPanel middlePanel = new JPanel(new GridLayout(2, 1));
        middlePanel.add(majorLabel);
        middlePanel.add(yearLabel);
        // set a blue border for middlePanel
        middlePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        setLayout(new BorderLayout());
        this.add(upperPanel, BorderLayout.NORTH);
        // this.add(middlePanel, BorderLayout.CENTER);
    }

}
