package com.team5.cowboy_brothers;


//import java.util.*;
//import com.team5.cowboy_brothers.GameMaster;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cowboy_bros_Menu extends JFrame {

    // Define the different game states
    public enum GameState {
        MAIN_MENU,
        LEVEL_SELECT,
        GAMEPLAY,
        PAUSE_MENU, 
        WIN_MENU,
        LOSE_MENU
    }
    //ActionListener listener  = event -> timerLabel.setText(Instant.now().toString());
    final int DELAY = 1000;
    int v=99;
    // Milliseconds between timer ticks
    Timer gamePlayTimer = new Timer(DELAY, null);
    PauseButtonValue tempPauseValue;

    
    //private Timer t1=new Timer();
            
    private GameState currentState = GameState.MAIN_MENU; // Initial state
    private JPanel mainMenuPanel;
    private JPanel levelSelectPanel;
    public GamePanel gameplayPanel;
    private JPanel pauseMenuPanel;
    private JPanel winMenuPanel;
    private JPanel loseMenuPanel;
    
    KeyHandler KH;

    public Cowboy_bros_Menu() {
        this.gameplayPanel = new GamePanel();
        setTitle("Cowboy Brothers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        //this.t1=t1;

        // Create the panels for different states
        createMainMenuPanel();
        createLevelSelectPanel();
        createGameplayPanel();
        createPauseMenu();

        // Initialize the singleton Cowboy_brothers.olly (if not already initialized)
        createWinMenu();
        createLoseMenu();

        
        // Display the main menu on start
        switchState(GameState.MAIN_MENU);

        setVisible(true);
        //Add key listener
        KH = new KeyHandler();
        gameplayPanel.addKeyListener(KH);
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
                Cowboy_brothers.olly.ResetPlayerProgress(); // Reset progress
            }
        });
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
                    if(Cowboy_brothers.olly.CheckLevelUnlocked(levelNumber))
                    {
                        switchState(GameState.GAMEPLAY);  // Switch to gameplay
                        Cowboy_brothers.olly.LoadLevel(levelNumber);
                    }
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
        
        gameplayPanel.setLayout(new BorderLayout());
        //JLabel label = new JLabel("Gameplay is happening here!", SwingConstants.CENTER);
        //label.setFont(new Font("Arial", Font.BOLD, 32));
        //gameplayPanel.add(label, BorderLayout.CENTER);
        
        // Add a back button to return to the level select screen
        JButton backButton = new JButton("PAUSE");
        backButton.setFont(new Font("Arial", Font.BOLD, 24));
        gameplayPanel.add(backButton, BorderLayout.SOUTH);
        
        //Add a timer as a label at top of screen
        timerLabel = new JLabel("",SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.PLAIN,15));
        timerLabel.setText("Timer: 100");
        gameplayPanel.add(timerLabel, BorderLayout.NORTH);
        gameplayPanel.requestFocusInWindow();
        
        
        
        
        
        

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("RECEIVED BACK BUTTON INPUT");
                switchState(GameState.PAUSE_MENU);  // Send to Pause menu
                gamePlayTimer.stop();
            }
        });
        
        //Timer Action will change the label to show time and switch the game state back to level select at 0
        gamePlayTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(v==0){
                    gamePlayTimer.stop();
                    v=99;
                    
                    switchState(GameState.LOSE_MENU);
                }else{
                    timerLabel.setText("Timer: "+v);
                    v--;
                }
            }
        });
        add(gameplayPanel, BorderLayout.CENTER);
    }

    private void createWinMenu() {
    winMenuPanel = new JPanel();
    winMenuPanel.setLayout(new GridLayout(2, 1, 10, 10));
    
    JLabel winLabel = new JLabel("You Win!", SwingConstants.CENTER);
    winLabel.setFont(new Font("Arial", Font.BOLD, 32));
    JButton nextLevelButton = new JButton("Next Level");
    JButton mainMenuButton = new JButton("Main Menu");
    
    winMenuPanel.add(winLabel);
    winMenuPanel.add(nextLevelButton);
    winMenuPanel.add(mainMenuButton);
    
    add(winMenuPanel, BorderLayout.CENTER);
    
    nextLevelButton.addActionListener(e -> {
        // Logic to load the next level
        switchState(GameState.LEVEL_SELECT);
    });
    
    mainMenuButton.addActionListener(e -> {
        switchState(GameState.MAIN_MENU);
    });
    }

    private void createLoseMenu() {
    loseMenuPanel = new JPanel();
    loseMenuPanel.setLayout(new GridLayout(2, 1, 10, 10));
    
    JLabel loseLabel = new JLabel("You Lose!", SwingConstants.CENTER);
    loseLabel.setFont(new Font("Arial", Font.BOLD, 32));
    JButton retryButton = new JButton("Retry");
    JButton mainMenuButton = new JButton("Main Menu");
    
    loseMenuPanel.add(loseLabel);
    loseMenuPanel.add(retryButton);
    loseMenuPanel.add(mainMenuButton);
    
    add(loseMenuPanel, BorderLayout.CENTER);
    
    retryButton.addActionListener(e -> {
        // Logic to retry the level
        switchState(GameState.GAMEPLAY);
         // Reset player state for the retry
         
    });
    
    mainMenuButton.addActionListener(e -> {
        switchState(GameState.MAIN_MENU);
    });
    }
    //create a pause menu
    private void createPauseMenu(){
        pauseMenuPanel = new JPanel();
        pauseMenuPanel.setLayout(new GridLayout(1, 3, 10,10));
        
        JButton retryButton = new JButton("Retry");
        retryButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 24));
        
        JButton levelSel =  new JButton("Level Select");
        levelSel.setFont(new Font("Arial", Font.BOLD, 24));
        
        pauseMenuPanel.add(retryButton);
        pauseMenuPanel.add(continueButton);
        pauseMenuPanel.add(levelSel);
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //reset all progress and restart the level
                Cowboy_brothers.olly.player.setBulletCountToFull();
                v=99;
                timerLabel.setText("Timer: 100");
                switchState(GameState.GAMEPLAY);
            }
        });
        
        continueButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               //continue from the pause menu
               tempPauseValue=new PauseButtonValue();
               switchState(GameState.GAMEPLAY);
               
           } 
        });
        levelSel.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               //return to level select
               Cowboy_brothers.olly.IncrementMaxLevelUnlocked();
               switchState(GameState.LEVEL_SELECT);
           } 
        });
        add(pauseMenuPanel, BorderLayout.CENTER);
    }
    // Function to switch between game states
    public void switchState(GameState Menu) {
        currentState = Menu;

        // Show/hide panels based on current state
        mainMenuPanel.setVisible(currentState == GameState.MAIN_MENU);
        levelSelectPanel.setVisible(currentState == GameState.LEVEL_SELECT);
        gameplayPanel.setVisible(currentState == GameState.GAMEPLAY);
        pauseMenuPanel.setVisible(currentState == GameState.PAUSE_MENU);
        winMenuPanel.setVisible(currentState == GameState.WIN_MENU);
        loseMenuPanel.setVisible(currentState == GameState.LOSE_MENU);
        //Add a notifier to a timer if gameplay starts
        
        if(currentState==GameState.GAMEPLAY){
            //call Timer Start and have it update the label in reference  to current time
            gamePlayTimer.start();
            gameplayPanel.requestFocus();
        }
    }
    
    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
        
        
    }

    public void EndProgram(int exitCode) {
        System.out.println("-- End program execution --");
        System.exit(exitCode);
    }
    //Way to return GameState
    public GameState getGameState(){
        return currentState;
    }
        
}
