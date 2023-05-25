package Boundary;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *     the Histogram panel, used to display the histogram of a student's marks
 */

public class Histogram extends JPanel {
    private int[] data;
    private int step;

    public Histogram(int[] data, int step) {
        this.data = data;
        this.step = step;

        // Set outer border
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

//        JFrame frame = new JFrame("Histogram");
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setSize(1200, 900);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        frame.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int barWidth = getWidth() / data.length - 10;

        int MaxMark = getMax();
        int MinMark = getMin();
        int MarkStep = (MaxMark - MinMark) / step;

        int[] frequency = new int[MarkStep + 1];
        for (int i = 0; i < data.length; i++) {
            int index = (data[i] - MinMark) / MarkStep;
            frequency[index]++;
        }
        MarkStep++;

        // 绘制直方图的每个条形
        for (int i = 0; i < MarkStep; i++) {
            int x = i * (barWidth + 10) + 10;
            int barHeight = (int) (((double) frequency[i] / Arrays.stream(frequency).max().getAsInt() * getHeight()) * 0.9);

            int y = getHeight() - barHeight - 20;
            g.fillRect(x, y, barWidth, barHeight);

            // 绘制频次标签
            String label = String.valueOf(frequency[i]);
            g.drawString(label, x, y - 10);
        }

        // 绘制X轴和Y轴
        g.drawLine(0, getHeight(), getWidth(), getHeight()); // X轴
        g.drawLine(0, 0, 0, getHeight()); // Y轴
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