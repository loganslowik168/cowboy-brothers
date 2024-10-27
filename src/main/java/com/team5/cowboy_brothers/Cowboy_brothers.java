package com.team5.cowboy_brothers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cowboy_brothers extends JFrame {

    // Define the different game states
    private enum GameState {
        MAIN_MENU,
        LEVEL_SELECT,
        GAMEPLAY
    }

    private GameState currentState = GameState.MAIN_MENU; // Initial state
    private JPanel mainMenuPanel;
    private JPanel levelSelectPanel;
    private JPanel gameplayPanel;

    public Cowboy_brothers() {
        setTitle("Cowboy Brothers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

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
    public static void main(String[] args) { // main function
        System.out.println("-- Begin program execution --");
        new Cowboy_brothers();
        //Timer timer=new Timer();
        //TimerTask task = new Helper();
        
        //timer.schedule(task,1000,10000);
         // Create an instance of Lvl1
        Lvl1 level1 = new Lvl1();
        
        // Instantiate the player with hardcoded stats
        int[] highScores = {0, 0, 0, 0, 0}; // Example high scores
        Player player = new Player(3, 6, 0, 0, highScores);
        
        // Set the player starting position
        double[] startingPos = level1.getStartingPosition();
        player.setPosition(startingPos[0], startingPos[1]);
        
        // Display player's current position and health
        System.out.println("Player started at position: " + player.getPosition()[0] + ", " + player.getPosition()[1]);
        System.out.println("Player health: " + player.getCurrentHealth());
        

    }

    // Create the Level Select panel with level buttons
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

    // Create a simple Gameplay panel (can be extended later)
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

    // Function to switch between game states
    private void switchState(GameState newState) {
        currentState = newState;

        // Show/hide panels based on current state
        mainMenuPanel.setVisible(currentState == GameState.MAIN_MENU);
        levelSelectPanel.setVisible(currentState == GameState.LEVEL_SELECT);
        gameplayPanel.setVisible(currentState == GameState.GAMEPLAY);
    }

    public void EndProgram(int exitCode) {
        System.out.println("-- End program execution --");
        System.exit(exitCode);
    }

    private void resetProgress() {
        System.out.println("Progress has been reset.");
    }

    
}
