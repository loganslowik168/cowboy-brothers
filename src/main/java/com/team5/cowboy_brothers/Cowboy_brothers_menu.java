package com.team5.cowboy_brothers;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cowboy_brothers_menu extends JFrame {

    // Define the different game states
    private enum GameState {
        MAIN_MENU,
        LEVEL_SELECT,
        GAMEPLAY,
        PAUSE_MENU
    }
    final int Del=1000;
    int v=100;
    Timer t = new Timer(Del,null);

    private GameState currentState = GameState.MAIN_MENU; // Initial state
    private JPanel mainMenuPanel;
    private JPanel levelSelectPanel;
    private JPanel gameplayPanel;
    private JPanel pauseMenuPanel;

    public Cowboy_brothers_menu() {
        setTitle("Cowboy Brothers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());

        // Create the panels for different states
        createMainMenuPanel();
        createLevelSelectPanel();
        createGameplayPanel();
        createPauseMenu();

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
    
    
    
    //Main
    public static void main(String[] args) { // main function
        System.out.println("-- Begin program execution --");
        new Cowboy_brothers_menu();
        
        
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
    
    
    
    JLabel timerLabel;
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
        
        //Add a timer as a label at top of screen
        timerLabel = new JLabel("",SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN,15));
        timerLabel.setText("Timer");
        gameplayPanel.add(timerLabel, BorderLayout.NORTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchState(GameState.PAUSE_MENU);  // Send to Pause menu
                t.stop();
                
                
                timerLabel.setText("Timer");  // Return to level select
            }
        });
        
        //Timer Action will change the label to show time and switch the game state back to level select at 0
        t.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(v==0){
                    t.stop();
                    timerLabel.setText("Timer: 0");
                    //label.setText("GameOver");
                    v=100;
                    
                    switchState(GameState.PAUSE_MENU);
                }else{
                    timerLabel.setText("Timer: "+v);
                    v--;
                }
            }
        });

        add(gameplayPanel, BorderLayout.CENTER);
    }
    
    private void createPauseMenu(){
        pauseMenuPanel = new JPanel();
        pauseMenuPanel.setLayout(new GridLayout(1, 3, 10,10));
        
        JButton retyButton = new JButton("Rety");
        retyButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton levelSel =  new JButton("Level Select");
        levelSel.setFont(new Font("Arial", Font.BOLD, 24));
        
        pauseMenuPanel.add(retyButton);
        pauseMenuPanel.add(continueButton);
        pauseMenuPanel.add(levelSel);
        
        
        
        
        retyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset all progress and restart the level
                v=100;
                switchState(GameState.GAMEPLAY);
            }
        });
        
        continueButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               //continue from the pause menu
               switchState(GameState.GAMEPLAY);
               
           } 
        });
        
        
        levelSel.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               //return to level select
               v=100;
               switchState(GameState.LEVEL_SELECT);
           } 
        });
        
        
        
        
        add(pauseMenuPanel, BorderLayout.CENTER);
    }
    
    
    

    // Function to switch between game states
    private void switchState(GameState newState) {
        currentState = newState;

        // Show/hide panels based on current state
        mainMenuPanel.setVisible(currentState == GameState.MAIN_MENU);
        levelSelectPanel.setVisible(currentState == GameState.LEVEL_SELECT);
        gameplayPanel.setVisible(currentState == GameState.GAMEPLAY);
        pauseMenuPanel.setVisible(currentState == GameState.PAUSE_MENU);
        //Add a notifier to a timer if gameplay starts
        
        if(currentState==GameState.GAMEPLAY){
        //call Timer Start and have it update the label in reference  to current time
        t.start();
        
        //Create a new class object to show the Player()
        }
    }

    public void EndProgram(int exitCode) {
        System.out.println("-- End program execution --");
        System.exit(exitCode);
    }

    private void resetProgress() {
        System.out.println("Progress has been reset.");
    }

    
}
