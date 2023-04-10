package UIDisplay;

import FileHandler.UserInfoHandler;

import javax.swing.*;
import java.awt.*;

public class ModuleItem extends JPanel {
    private UserInfoHandler userInfoHandler;
    private int num;
    public ModuleItem(int col,String name) {
        userInfoHandler=getUserInfo("Data/Modules&Marks.csv");
        JLabel label1=new JLabel();
        JLabel label2=new JLabel();
        String ModuleName=getModuleName(col,name);
        int Mark=getModuleMark(name,ModuleName);
        this.setLayout(null);
        this.setSize(200,150);
        this.setBackground(Color.LIGHT_GRAY);
        label1.setText(ModuleName);
        label2.setText(String.valueOf(Mark));
        Font font = new Font("Arial", Font.BOLD, 16);
        label1.setFont(font);
        label2.setFont(font);
        this.add(label1);
        this.add(label2);
        label1.setBounds(10,0,100,70);
        label2.setBounds(10,75,100,70);
        this.setVisible(true);
    }
    public String getModuleName(int col,String name){
        userInfoHandler.open(name);
        String[] data = userInfoHandler.getHeaders();
        return data[col];
    }
    public int getModuleMark(String name,String module){
        userInfoHandler.open(name);
        String ModuleMark=userInfoHandler.getData(module);
        return Integer.parseInt(ModuleMark);
    }
    public UserInfoHandler getUserInfo(String file){
        return new UserInfoHandler(file);
    }

    public int getNum() {
        num=userInfoHandler.getHeaders().length-1;
        return num;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw a border around the panel
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
