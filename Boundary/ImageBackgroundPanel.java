package Boundary;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.*;
import java.io.*;

public class ImageBackgroundPanel extends JPanel {

    private BufferedImage image; // the original image
    private int width; // the width of the panel
    private int height; // the height of the panel

    public ImageBackgroundPanel(String imagePath, int windowHeight, double i) {
        // this.image is created from the image at imagePath
        this.image = null;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.height = windowHeight;
        System.out.println("width: " + width + " height: " + height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // calculate the scaling factor based on the height ratio
        double scale = (double) height / image.getHeight();

        // calculate the scaled width and height of the image
        int scaledWidth = (int) (image.getWidth() * scale);
        int scaledHeight = (int) (image.getHeight() * scale);

        // draw the scaled and cropped image on the panel
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, this);
    }
}