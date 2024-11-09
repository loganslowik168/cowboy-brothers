package com.team5.cowboy_brothers;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TestSpriteDrawer {

    private BufferedImage spriteImage;

    // Constructor accepts an existing JFrame
    public TestSpriteDrawer(JFrame frame) {
        // Ensure the frame has a content pane and set up the drawing panel
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);  // Ensure proper rendering

                // Draw the sprite image at position (600, 400)
                if (spriteImage != null) {
                    g.drawImage(spriteImage, 600, 400, this);
                }
            }
        };

        // Add the custom JPanel to the existing JFrame
        frame.setContentPane(drawingPanel);

        // Load the sprite image
        loadSpriteImage();

        // Set up a timer to repaint the panel every 100ms (0.1 seconds)
        Timer timer = new Timer(100, e -> drawingPanel.repaint());
        timer.start();
    }

    private void loadSpriteImage() {
        try {
            // Load the sprite from the file system (adjust path if necessary)
            File spriteFile = new File("sprites/player.png");
            spriteImage = ImageIO.read(spriteFile);  // Use ImageIO to load the image into a BufferedImage

            // Scale the image to 500% of its original size
            int newWidth = spriteImage.getWidth() * 2; // 500% scaling
            int newHeight = spriteImage.getHeight() * 2; // 500% scaling

            // Create a new BufferedImage for the scaled image
            BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();

            // Draw the original image into the scaled image
            g2d.drawImage(spriteImage, 0, 0, newWidth, newHeight, null);
            g2d.dispose();

            // Set the scaled image
            spriteImage = scaledImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Print a message to indicate that the main function was entered
        System.out.println("Entered the main function");

        // Create an existing JFrame (for example, let's assume it's already created)
        JFrame existingFrame = new JFrame("Existing JFrame");

        // Set basic frame settings
        existingFrame.setSize(1200, 800); // Adjust the size as needed
        existingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        existingFrame.setLocationRelativeTo(null); // Center the window on the screen
        existingFrame.setVisible(true); // Make sure the frame is visible

        // Create an instance of TestSpriteDrawer and pass the existing JFrame
        new TestSpriteDrawer(existingFrame);
    }
}
