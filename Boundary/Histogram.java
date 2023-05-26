package Boundary;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *       the Histogram panel, used to display the histogram of a student's marks
 */

public class Histogram extends JPanel {
    private int[] data;
    private int step;

    public Histogram(int[] data, int step) {
        this.data = data;
        this.step = step;

        // Set outer border
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        // JFrame frame = new JFrame("Histogram");
        // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // frame.setSize(1200, 900);
        // frame.setLocationRelativeTo(null);
        // frame.setVisible(true);
        // frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int MaxMark = 100;
        int MinMark = 60;
        int MarkStep = 5;
        int barWidth = getWidth() / (MarkStep) - 10;

        int[] frequency = new int[MarkStep];
        // Below 60, 60-70, 70-80, 80-90, 90-100
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 60) {
                frequency[0]++;
            } else if (data[i] < 70) {
                frequency[1]++;
            } else if (data[i] < 80) {
                frequency[2]++;
            } else if (data[i] < 90) {
                frequency[3]++;
            } else {
                frequency[4]++;
            }
        }

        // Color from red to green
        Color[] colors = {
                new Color(231, 76, 60),
                new Color(255, 229, 105),
                new Color(255, 255, 255),
                new Color(174, 214, 241),
                new Color(41, 128, 185)
        };

        // Draw bars
        for (int i = 0; i < MarkStep; i++) {
            // Color
            g.setColor(colors[i]);

            int x = i * (barWidth + 10) + 10;
            int barHeight = (int) (((double) frequency[i] / Arrays.stream(frequency).max().getAsInt() * getHeight())
                    * 0.9);

            int y = getHeight() - barHeight - 20;
            g.fillRect(x, y, barWidth, barHeight);

            // Draw label
            String label = String.valueOf(frequency[i]);
            g.drawString(label, x, y - 10);
        }

        // Draw axis
        g.setColor(Color.BLACK);
        g.drawLine(0, getHeight(), getWidth(), getHeight());
        // g.drawLine(0, 0, 0, getHeight());
    }

    private int getMax() {
        int max = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > max)
                max = data[i];
        }
        return max;
    }

    private int getMin() {
        int min = 100;
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min)
                min = data[i];
        }
        return min;
    }
}