package com.team5.cowboy_brothers;

import java.awt.Frame;
import javax.swing.JFrame;

/**
 *
 * @author matth
 */
public class GameMaster {
    
    //public static GameMaster olly;
    int[] hs = {0,0,0,0,0};
    public int Selectedlvl;
    public Cowboy_bros_Menu VisibleMenu;
    public Player player;// = new Player(3,6,1,0,hs,0,0,VisibleMenu.gameplayPanel);

    GameWorld gameWorld; 

    
    public GameMaster()
    {
        VisibleMenu = new Cowboy_bros_Menu();
        player = new Player(3,6,1,0,hs,400,40,VisibleMenu.gameplayPanel);
        VisibleMenu.gameplayPanel.setPlayer(player);

        gameWorld = new GameWorld(VisibleMenu);
        Selectedlvl = 0;

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
        switch(lvl)
        {
            case 1 -> 
            {
                System.out.println("Load level 1. --olly");
                Lvl1 lvl1 = new Lvl1();
                Selectedlvl = lvl;
            }
            case 2 -> 
            {
                System.out.println("Load level 1. --olly");
                Lvl2 lvl2 = new Lvl2();
                Selectedlvl = lvl;
            }
            case 3 -> System.out.println("Load level 3. --olly");
            case 4 -> System.out.println("Load level 4. --olly");
            case 5 -> System.out.println("Load level 5. --olly");
            default -> System.out.println("ERROR. Please select a level between 1 and 5 inclusive! --olly");
        }
    }
    public boolean CheckLevelUnlocked(int lvl) {return lvl<=player.getMaxUnlockedLevel();}
    public void IncrementMaxLevelUnlocked() {player.setMaxUnlockedLevel(player.getMaxUnlockedLevel()+1);}
    public void ResetPlayerProgress() {player = new Player(3,6,1,0,hs,0,0,VisibleMenu.gameplayPanel);System.out.println("PROGRESS HAS BEEN RESET");}
}
