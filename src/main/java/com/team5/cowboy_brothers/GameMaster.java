package com.team5.cowboy_brothers;
import com.team5.cowboy_brothers.GamePanel;
import java.awt.Frame;
import javax.swing.JFrame;


public class GameMaster {
    
    //public static GameMaster olly;
    int[] hs = {0,0,0,0,0};
    public Cowboy_bros_Menu VisibleMenu;
    public GamePanel gameplayPanel;
    public Player player;// = new Player(3,6,1,0,hs,0,0,VisibleMenu.gameplayPanel);

    public GameWorld gameWorld; 
    public Map LoadedLevel;
    
    public boolean IsThereASaloon = false;
    public int Selectedlvl;
    public GameMaster()
    {
        VisibleMenu = new Cowboy_bros_Menu();
        player = new Player(3,6,5,0,hs,400,40,VisibleMenu.gameplayPanel); //change max unlocked level (3rd param) back to 1
        VisibleMenu.gameplayPanel.setPlayer(player);
        gameplayPanel = VisibleMenu.gameplayPanel;
        gameWorld = new GameWorld(VisibleMenu);

    }
    
    
    public JFrame GetActiveJFrame()
    {
        Frame[] frames = JFrame.getFrames();

        // Loop through the frames array and check for the first instance of JFrame
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {  // Check if the frame is actually a JFrame
                JFrame activeFrame = (JFrame) frame; // Safely cast it to JFrame
                return activeFrame;
                
            }
        }
        return null;
    }
    
    public void LoadLevel(int lvl)
    {
        // Reset the current level state
        clearBossAndDynamite();
        resetCurrentLevel();
        switch(lvl)
        { 
            case 1 -> 
            {
                System.out.println("Load level 1. --olly");
                LoadedLevel = new Lvl1();
                new HUD(player,1,VisibleMenu.gameplayPanel);
            }
            case 2 -> 
            {
                System.out.println("Load level 2. --olly");
                LoadedLevel = new Lvl2();
                new HUD(player,2,VisibleMenu.gameplayPanel);
            }
            case 3 -> 
            {
                System.out.println("Load level 3. --olly");
                new HUD(player,3,VisibleMenu.gameplayPanel);
                LoadedLevel = new Lvl3();
            }
            case 4 -> 
            {
                System.out.println("Load level 4. --olly");
                new HUD(player,4,VisibleMenu.gameplayPanel);
                LoadedLevel = new Lvl4();
            }
            case 5 -> 
            {
                System.out.println("Load level 5. --olly");
                LoadedLevel = new Lvl5();
                new HUD(player,5,VisibleMenu.gameplayPanel);
            }
            default -> System.out.println("ERROR. Please select a level between 1 and 5 inclusive! --olly");
        }
    }
    private void resetCurrentLevel() {
        // Reset player state
        player.resetPosition(40, 380); // Example starting position
        player.setMaxUnlockedLevel(0); // Reset max level if needed
        player.setBulletCountToFull(); // Reset ammo
        Cowboy_brothers.olly.player.currentHealth = Cowboy_brothers.olly.player.getMaxHealth(); // Reset health to maximum

        // Clear game objects in the GamePanel
        gameplayPanel.clearGameObjects(); // Implement this method in GamePanel
    }
    public boolean CheckLevelUnlocked(int lvl) {return lvl<=player.getMaxUnlockedLevel();}

    public void IncrementMaxLevelUnlocked() {player.setMaxUnlockedLevel(player.getMaxUnlockedLevel()+1);}
    

private void clearBossAndDynamite() {
    // Clear the boss if it exists
    if (gameWorld.boss != null) {
        gameWorld.boss.Dispose(); // Dispose of the boss object
        gameWorld.boss = null; // Set boss reference to null
    }
    
    // Clear all dynamite objects (assuming you have a way to track them)
    for (int i = gameWorld.moveableObjects.size() - 1; i >= 0; i--) {
        MoveableGameObject obj = gameWorld.moveableObjects.get(i);
        if (obj instanceof Bomb) { // Check if the object is a Bomb
            obj.Dispose(); // Dispose of the bomb object
            gameWorld.moveableObjects.remove(i); // Remove it from the list
        }
    }
}

    public void ResetPlayerProgress() {player = new Player(3,6,1,0,hs,0,0,VisibleMenu.gameplayPanel);System.out.println("PROGRESS HAS BEEN RESET");}
}
