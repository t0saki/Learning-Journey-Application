package Boundary;

import javax.swing.*;

import Control.FontManager;
import java.awt.*;
import javax.swing.border.*;
import java.awt.geom.*;

import Control.*;

/**
 * @author Zhengxiao Wu
 * @author Ruitian Yang
 * @date 2023/05/25
 *     the base class for all panels
 *     contains a sidebar and a content panel
 */

public class BaseDisplay extends JPanel {

    public Boolean enableRoundedCorner = false;

    JPanel contentPanel = new JPanel();
    String displayType = "Base";

    public BaseDisplay(String type) {
        // Add some paddings to the sidebar
        displayType = "    " + type;

        // Create a sidebar logo with JLabel
        JLabel logo = new JLabel(displayType);
        logo.setFont(FontManager.getLatoRegular(16));
        logo.setForeground(Color.WHITE);

        setLayout(new GridLayout(0, 1));

        // debug
        // logo.setBorder(new MatteBorder(15, 15, 15, 15, BaseDisplay.unselectedColor));
        // logo.setBorder(BorderFactory.createLineBorder(Color.RED));

        // this allows its content to be centered
        // setLayout(new GridBagLayout());
        add(logo);

        setBorder(new MatteBorder(15, 15, 15, 15, GlobalColors.unselectedColor));
        setBackground(GlobalColors.unselectedColor);
    }

    public JPanel getContentPanel() {
        // Print panel size
        // System.out.println(contentPanel.getSize());

        // contentPanel.setSize(800, 600);

        return contentPanel;
    }

    @Override
    public void paint(Graphics g) {
        if (!enableRoundedCorner) {
            super.paint(g);
            return;
        }

        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;

        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX + 15, fieldY + 15,
                fieldWeight - 30, fieldHeight - 30, 8, 8);

        g.setClip(rect);

        super.paint(g);
    }
}
