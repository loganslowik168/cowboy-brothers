package com.team5.cowboy_brothers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Cowboy_brothers extends JFrame implements KeyListener {

    private int boxX = 150; // Initial X position of the box
    private int boxY = 150; // Initial Y position of the box
    private final int BOX_SIZE = 50; // Size of the box
    private final int MOVE_SPEED = 5; // Movement speed of the box

    public Cowboy_brothers() {
        setTitle("Cowboy Brothers Key Input");
        setSize(800, 800); // Set window size to 800x800
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addKeyListener(this); //WATCH leaking this
        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("-- Begin program execution --");
        new Cowboy_brothers();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP ->    // Move up
                boxY -= MOVE_SPEED;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN ->  // Move down
                boxY += MOVE_SPEED;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT ->  // Move left
                boxX -= MOVE_SPEED;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> // Move right
                boxX += MOVE_SPEED;
            case KeyEvent.VK_ESCAPE ->               // Terminate the program
                EndProgram(0);
        }
        updateTitle(); // Update the window title to reflect the coordinates
        repaint(); // Repaint the frame to update the box position
    }
    
    public void EndProgram(int exitCode){
        System.out.println("-- End program execution --");
        System.exit(exitCode);
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.fillRect(boxX, boxY, BOX_SIZE, BOX_SIZE); // Draw the red box

        // Draw a smiley face in the center of the box
        drawSmileyFace(g, boxX + BOX_SIZE / 2, boxY + BOX_SIZE / 2);
    }
    
    

    private void drawSmileyFace(Graphics g, int centerX, int centerY) {
        int radius = BOX_SIZE / 2 - 10; // Radius of the face
        // Draw face
        g.setColor(Color.YELLOW);
        g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        
        // Draw eyes
        g.setColor(Color.BLACK);
        g.fillOval(centerX - radius / 2, centerY - radius / 2, radius / 5, radius / 5); // Left eye
        g.fillOval(centerX + radius / 5, centerY - radius / 2, radius / 5, radius / 5); // Right eye
        
        // Draw smile
        g.drawArc(centerX - radius / 2, centerY - radius / 4, radius, radius / 2, 0, -180);
    }

    private void updateTitle() {
        setTitle("Cowboy Brothers Key Input - X: " + boxX + ", Y: " + boxY);
    }
}
