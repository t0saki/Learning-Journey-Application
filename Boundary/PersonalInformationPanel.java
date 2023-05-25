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

    /**
     * @param data[]
     * @return String[]
     */
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

    public ImageIcon setImageSize(String fileName, int aimWidth, int aimHeight) {
        ImageIcon icon = new ImageIcon(fileName);

        // rescale the image and return the new image
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(aimWidth, aimHeight, Image.SCALE_AREA_AVERAGING);
        return new ImageIcon(newImage);
    }

    public PersonalInformationPanel(String studentID) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

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

        int aimWidth = 200;
        int aimHeight = 200;
        photo = setImageSize(file.getPath(), aimWidth, aimHeight);

        JLabel photoLabel = new JLabel(photo);
        photoLabel.setPreferredSize(new Dimension(aimWidth, aimHeight));
        // photoLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea nameLabel = new JTextArea(name);
        JTextArea idLabel = new JTextArea("BUPT #" + studentId);
        nameLabel.setFont(FontManager.getLatoBold(20));
        idLabel.setFont(FontManager.getLatoLight(18));
        nameLabel.setForeground(GlobalColors.solidWhite);
        idLabel.setForeground(GlobalColors.solidWhite);
        nameLabel.setBackground(GlobalColors.lighterBlack);
        idLabel.setBackground(GlobalColors.lighterBlack);

        // nameLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.solidOrange));
        // idLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.solidOrange));

        JTextArea majorLabel = new JTextArea(major);
        JTextArea yearLabel = new JTextArea(entranceYear);
        majorLabel.setFont(FontManager.getLatoRegular(14));
        yearLabel.setFont(FontManager.getLatoRegular(14));
        majorLabel.setForeground(GlobalColors.solidWhite);
        yearLabel.setForeground(GlobalColors.solidWhite);
        majorLabel.setBackground(GlobalColors.lighterBlack);
        yearLabel.setBackground(GlobalColors.lighterBlack);
        majorLabel.setWrapStyleWord(true);
        majorLabel.setLineWrap(true);

        JPanel p1 = new JPanel(new GridBagLayout());
        JPanel p2 = new JPanel(new GridBagLayout());
        JPanel p3 = new JPanel(new GridBagLayout());
        JPanel p4 = new JPanel(new GridBagLayout());
        JPanel p5 = new JPanel(new GridBagLayout());
        JPanel p6 = new JPanel(new GridBagLayout());

        // p1.setBackground(GlobalColors.solidYellow);
        // p2.setBackground(GlobalColors.solidRed);
        // p3.setBackground(GlobalColors.lighterBlack);
        // p4.setBackground(GlobalColors.solidBrown);
        // p5.setBackground(GlobalColors.solidYellow);
        // p6.setBackground(GlobalColors.solidRed);

        p1.setBackground(GlobalColors.lighterBlack);
        p2.setBackground(GlobalColors.lighterBlack);
        p3.setBackground(GlobalColors.lighterBlack);
        p4.setBackground(GlobalColors.lighterBlack);
        p5.setBackground(GlobalColors.lighterBlack);
        p6.setBackground(GlobalColors.lighterBlack);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        // blank
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        this.add(p1, gbc);

        // photo
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p2.add(photoLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.08;
        this.add(p2, gbc);

        // name and id
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p3.add(nameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p3.add(idLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.02;
        this.add(p3, gbc);

        // blank
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        this.add(p4, gbc);

        // major and entrance year
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p5.add(majorLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p5.add(yearLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.2;
        this.add(p5, gbc);

        // blank
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.3;
        this.add(p6, gbc);

        // gbc.gridx = 0;
        // gbc.gridy = 2;
        // gbc.gridwidth = 1;
        // gbc.gridheight = 1;
        // gbc.weightx = 1;
        // gbc.weighty = 0.2;
        // gbc.fill = GridBagConstraints.BOTH;
        // gbc.anchor = GridBagConstraints.CENTER;
        // upperPanel.add(idLabel, gbc);

        // // set a red border for upperPanel
        // upperPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        // majorLabel.setFont(FontManager.getLatoRegular(14));
        // yearLabel.setFont(FontManager.getLatoRegular(14));

        // JPanel middlePanel = new JPanel(new GridLayout(2, 1));
        // middlePanel.add(majorLabel);
        // middlePanel.add(yearLabel);
        // // set a blue border for middlePanel
        // middlePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // setLayout(new BorderLayout());
        // this.add(upperPanel, BorderLayout.NORTH);
        // this.add(middlePanel, BorderLayout.CENTER);
    }

}
