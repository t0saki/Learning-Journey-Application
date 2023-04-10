package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;
import java.awt.*;

public class UserInfo extends JPanel {
    // Show a user's information
    public UserInfo() {
        UserInfoHandler userInfo = new UserInfoHandler("Data\\UserInfo.csv");
        userInfo.open("Li Hua");
        String[] data = userInfo.getData();
        JPanel userPanel=new JPanel();
        userPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setLayout(new BorderLayout());

        JLabel[] labels = new JLabel[data.length];
        for (int i = 0; i < data.length; i++) {
            labels[i] = new JLabel(data[i]);
        }

        // Show on this JPanel
        for (JLabel label : labels) {
            userPanel.add(label);
        }
        add(userPanel,"North");

        JPanel ItemPanel=new JPanel();
        ModuleItem firstModule=new ModuleItem(1,"Li Hua");
        ModuleItem[] moduleItem=new ModuleItem[firstModule.getNum()];
        moduleItem[0]=firstModule;
        ItemPanel.setLayout(new GridLayout((firstModule.getNum()/2)+1,2,10,10));
        ItemPanel.add(firstModule);
        for (int i=1;i<firstModule.getNum();i++){
            moduleItem[i]=new ModuleItem(i+1,"Li Hua");
            ItemPanel.add(moduleItem[i]);
        }
        ItemPanel.setSize(600,550);
        add(ItemPanel,"Center");

    }
}
