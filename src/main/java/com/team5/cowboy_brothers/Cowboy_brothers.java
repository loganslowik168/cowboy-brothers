package com.team5.cowboy_brothers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cowboy_brothers extends JFrame implements KeyListener, MouseListener{

    
     private enum GameState {
        MAIN_MENU,
        LEVEL_SELECT,
        GAMEPLAY
    }
    
    private GameState currentState = GameState.MAIN_MENU; // Initial state
    private JPanel mainMenuPanel;
    private JPanel levelSelectPanel;
    private JPanel gameplayPanel;
     
    private int boxX = 150; // Initial X position of the box
    private int boxY = 150; // Initial Y position of the box
    private final int BOX_SIZE = 50; // Size of the box
    private final int MOVE_SPEED = 5; // Movement speed of the box

    public Cowboy_brothers() {
        setTitle("Cowboy Brothers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        addKeyListener(this); //WATCH leaking this
        // Create the panels for different states
        createMainMenuPanel();
        createLevelSelectPanel();
        createGameplayPanel();

        // Display the main menu on start
        switchState(GameState.MAIN_MENU);

        setVisible(true);
    }
    
    // Create the Main Menu panel with buttons
    private void createMainMenuPanel() {
        mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton levelSelectButton = new JButton("LEVEL SELECT");
        JButton quitButton = new JButton("QUIT GAME");
        JButton resetButton = new JButton("RESET PROGRESS");

        // Style the buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 24);
        levelSelectButton.setFont(buttonFont);
        quitButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);

        mainMenuPanel.add(levelSelectButton);
        mainMenuPanel.add(resetButton);
        mainMenuPanel.add(quitButton);

        add(mainMenuPanel, BorderLayout.CENTER);

        // Button actions for Main Menu
        levelSelectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchState(GameState.LEVEL_SELECT);  // Switch to LEVEL_SELECT state
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EndProgram(0); // Exit the game
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetProgress(); // Reset progress
            }
        });
    }
    
    private void createLevelSelectPanel() {
        levelSelectPanel = new JPanel();
        levelSelectPanel.setLayout(new GridLayout(3, 2, 10, 10));

        // Create level buttons
        for (int i = 1; i <= 5; i++) {
            JButton levelButton = new JButton("Level " + i);
            levelButton.setFont(new Font("Arial", Font.BOLD, 24));
            levelSelectPanel.add(levelButton);

            int levelNumber = i; // Capture level number in the listener
            levelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("You selected Level " + levelNumber);
                    switchState(GameState.GAMEPLAY);  // Switch to gameplay
                }
            });
        }

        // Create a back button to return to the main menu
        JButton backButton = new JButton("BACK");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        levelSelectPanel.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchState(GameState.MAIN_MENU); // Return to the Main Menu
            }
        });

        add(levelSelectPanel, BorderLayout.CENTER);
    }
    
    private void createGameplayPanel() {
        gameplayPanel = new JPanel();
        gameplayPanel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Gameplay is happening here!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 32));
        gameplayPanel.add(label, BorderLayout.CENTER);

        // Add a back button to return to the level select screen
        JButton backButton = new JButton("BACK TO LEVEL SELECT");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        gameplayPanel.add(backButton, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchState(GameState.LEVEL_SELECT);  // Return to level select
            }
        });

        add(gameplayPanel, BorderLayout.CENTER);
    }
    
    private void switchState(GameState newState) {
        currentState = newState;

        // Show/hide panels based on current state
        mainMenuPanel.setVisible(currentState == GameState.MAIN_MENU);
        levelSelectPanel.setVisible(currentState == GameState.LEVEL_SELECT);
        gameplayPanel.setVisible(currentState == GameState.GAMEPLAY);
    }
    
    private void resetProgress() {
        System.out.println("Progress has been reset.");
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

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
}
