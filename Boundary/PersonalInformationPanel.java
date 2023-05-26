package Boundary;

import javax.swing.*;
import java.awt.*;

import Control.*;
import java.io.*;

/**
 * @author Ruitian Yang
 * @date 2023/05/25
 *       the personal information panel
 */
// attribution:
// href="https://www.freepik.com/free-vector/set-people-avatars-male-female-characters-faces_22535547.htm#query=png%20avatars&position=4&from_view=keyword&track=ais">Image
// by upklyak</a> on Freepik
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

    // hash the studentID and select an image
    private int selectImageIndex(String studentID, int imageNum) {
        int hash = studentID.hashCode();
        return hash % imageNum;
    }

    public PersonalInformationPanel(String studentID) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        setBackground(GlobalColors.lighterBlack);

        // Get user's information
        UserInfoHandler userInfo = new UserInfoHandler();
        userInfo.open("Data\\UserInfo.csv");
        int rowIndex = userInfo.getFirstRowIndexByHeaderAndVal("StudentId", studentID);
        String[] data = userInfo.getRow(rowIndex);

        // Parse data
        String[] parsedData = parseData(data);
        this.name = parsedData[0];
        this.studentId = parsedData[1];
        this.major = parsedData[2];
        this.entranceYear = parsedData[3];

        // Generate user's photo
        File folder = new File("Images\\Avatars");
        File[] files = folder.listFiles();
        int index = selectImageIndex(studentID, files.length);
        File file = files[index];

        int aimWidth = 220;
        int aimHeight = 220;
        photo = setImageSize(file.getPath(), aimWidth, aimHeight);

        JLabel photoLabel = new JLabel(photo);
        photoLabel.setPreferredSize(new Dimension(aimWidth, aimHeight));
        // photoLabel.setHorizontalAlignment(JLabel.CENTER);

        JLabel nameLabel = new JLabel(name);
        JLabel idLabel = new JLabel("BUPT #" + studentId);
        nameLabel.setFont(FontManager.getLatoBold(20));
        idLabel.setFont(FontManager.getLatoLight(18));
        nameLabel.setForeground(GlobalColors.solidWhite);
        idLabel.setForeground(GlobalColors.solidWhite);

        // nameLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.solidOrange));
        // idLabel.setBorder(BorderFactory.createLineBorder(GlobalColors.solidOrange));

        JLabel yearLabel = new JLabel("Start year - " + entranceYear);
        JLabel majorLabel = new JLabel(major);
        yearLabel.setFont(FontManager.getLatoRegular(16));
        majorLabel.setFont(FontManager.getLatoRegular(12));
        yearLabel.setForeground(GlobalColors.solidWhite);
        majorLabel.setForeground(GlobalColors.solidWhite);

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
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p5.add(yearLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        p5.add(majorLabel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        this.add(p5, gbc);

        // blank
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0.4;
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
