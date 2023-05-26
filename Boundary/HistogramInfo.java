package Boundary;

import Control.FontManager;

import javax.swing.*;

public class HistogramInfo extends JPanel {
        private int[] data;
        private int step;

        private Histogram histogram;

        public HistogramInfo(Histogram histogram) {
                this.histogram = histogram;

                // Set outer border
                setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

                // histogram = new Histogram(data, 10);

                // Header
                JPanel title = new JPanel();
                JLabel titlelable = new JLabel("<html><center>CourseMark Histogram</center></html>");
                titlelable.setFont(FontManager.getIBSCartooning(100));
                titlelable.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
                // userPanel.add(titlelable);
                // titlelable.setFont(new Font("", Font.PLAIN, 30));
                title.add(titlelable);

                // Add components
                setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
                add(title);

                // Add histogram
                add(histogram);

                // Add x axis
                JPanel xaxis = new JPanel();
                xaxis.setLayout(new BoxLayout(xaxis, BoxLayout.X_AXIS));
                xaxis.setBorder(BorderFactory.createEmptyBorder(10, 80, 0, 80));
                // xaxis.add(Box.createHorizontalGlue());
                xaxis.add(new JLabel("Below 60"));
                xaxis.add(Box.createHorizontalGlue());
                xaxis.add(new JLabel("60-70"));
                xaxis.add(Box.createHorizontalGlue());
                xaxis.add(new JLabel("70-80"));
                xaxis.add(Box.createHorizontalGlue());
                xaxis.add(new JLabel("80-90"));
                xaxis.add(Box.createHorizontalGlue());
                xaxis.add(new JLabel("90-100"));
                // xaxis.add(Box.createHorizontalGlue());

                add(xaxis);

        }

}
