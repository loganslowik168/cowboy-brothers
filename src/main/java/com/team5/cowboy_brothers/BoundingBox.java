
package com.team5.cowboy_brothers;
import java.awt.*;

public class BoundingBox {

    // Variables to store the four points of the box
    private int x1, y1, x2, y2, x3, y3, x4, y4;
    private GamePanel targetPanel;

    // Constructor to initialize the four points and the target JPanel
    public BoundingBox(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, GamePanel targetPanel) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.targetPanel = targetPanel;

        // Ensure the panel is repainted when the bounding box is created
        targetPanel.repaint();
    }

    // Method to draw the bounding box on the provided JPanel
    public void draw(Graphics g) {
        // Cast Graphics object to Graphics2D for better control over rendering
        Graphics2D g2d = (Graphics2D) g;

        // Set the color and stroke for drawing the bounding box
        g2d.setColor(Color.BLACK); // Color of the box
        g2d.setStroke(new BasicStroke(2)); // Line thickness

        // Draw lines between the 4 points to form the bounding box
        g2d.drawLine(x1, y1, x2, y2); // Line from point 1 to point 2
        g2d.drawLine(x2, y2, x3, y3); // Line from point 2 to point 3
        g2d.drawLine(x3, y3, x4, y4); // Line from point 3 to point 4
        g2d.drawLine(x4, y4, x1, y1); // Line from point 4 to point 1 (closing the box)
    }
}

    