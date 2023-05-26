package Boundary;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import Control.*;

/**
 * @author Ruitian Yang
 * @author Yurong He
 * @date 2023/05/25
 *       this is a customized button class
 */
public class MyButton extends JButton {
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    // private Color textColor;
    private int radius = 0;

    public MyButton(String buttonName) {
        this();
        setText(buttonName);
    }

    /**
     * @param color       the color of the button
     * @param colorOver   the color of the button when the mouse is over it
     * @param colorClick  the color of the button when the mouse is clicking it
     * @param borderColor the color of the border
     * @param textColor   the color of the text
     */
    public void setColors(Color color, Color colorOver, Color colorClick, Color borderColor, Color textColor) {
        this.color = color;
        this.colorOver = colorOver;
        this.colorClick = colorClick;
        this.borderColor = borderColor;
        // this.textColor = textColor;

        // Set colors
        setBackground(color);
        setForeground(textColor);
    }

    // Constructor
    public MyButton() {
        setColors(GlobalColors.lighterPurple, GlobalColors.darkerPurple, GlobalColors.lighterPurple,
                GlobalColors.lighterPurple, GlobalColors.solidWhite);
        setContentAreaFilled(false);

        // Add event mouse
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(colorOver);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(color);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent me) {
                setBackground(colorClick);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
            }
        });
    }

    // Set radius
    public void setRadius(int radius) {
        this.radius = radius;
    }

    // Paint Component
    @Override
    protected void paintComponent(Graphics grphcs) {
        // Paint Background
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Paint Border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());

        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(grphcs);
    }
}