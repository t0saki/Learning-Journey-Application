package Boundary;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class ImageBackgroundPanel extends JPanel {

    private BufferedImage image; // the original image
    private double i; // the horizontal scrolling factor
    private int width; // the width of the panel
    private int height; // the height of the panel

    public ImageBackgroundPanel(String imagePath, double i) {
        // this.image is created from the image at imagePath
        this.image = null;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.i = i;
        this.width = this.getWidth();
        this.height = this.getHeight();
        System.out.println("width: " + width + " height: " + height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // calculate the scaling factor based on the height ratio
        double scale = (double) height / image.getHeight();
        // double scale = (double) height / image.getHeight();

        // calculate the scaled width and height of the image
        int scaledWidth = (int) (image.getWidth() * scale);
        int scaledHeight = (int) (image.getHeight() * scale);

        // calculate the x coordinate of the image based on the scrolling factor
        int x = (int) ((width - scaledWidth) * i);

        // draw the scaled and cropped image on the panel
        g2d.drawImage(image, x, 0, scaledWidth, scaledHeight, this);
        // g2d.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}