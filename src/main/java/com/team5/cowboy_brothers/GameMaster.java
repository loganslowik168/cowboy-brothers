/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;
/**
 *
 * @author matth
 */
public class GameMaster {
    
    public static GameMaster olly;
    int[] hs = {0,0,0,0,0};
    protected Player player = new Player(3,6,1,0,hs,0,0);
    
    
    
    public GameMaster(){
       Cowboy_bros_Menu VisibleMenu =  new Cowboy_bros_Menu();
    }
    
    public static void main(String[] args) { 
        olly = new GameMaster();
        
        /*
        System.out.println("Creating test drawer");
                // Get all open frames (Frame instances)
        Frame[] frames = JFrame.getFrames();

        // Loop through the frames array and check for the first instance of JFrame
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {  // Check if the frame is actually a JFrame
                JFrame activeFrame = (JFrame) frame; // Safely cast it to JFrame
                System.out.println("Active JFrame found: " + activeFrame.getTitle());
                
                // Pass the active JFrame to TestSpriteDrawer
                //var tsd = new TestSpriteDrawer(activeFrame); // Pass the active frame to the TestSpriteDrawer
            }
        }
*/
    }
    
    public void LoadLevel(int lvl)
    {
        switch(lvl)
        {
            case 1 -> System.out.println("Load level 1. --olly");
            case 2 -> System.out.println("Load level 2. --olly");
            case 3 -> System.out.println("Load level 3. --olly");
            case 4 -> System.out.println("Load level 4. --olly");
            case 5 -> System.out.println("Load level 5. --olly");
            default -> System.out.println("ERROR. Please select a level between 1 and 5 inclusive! --olly");
        }
    }
    public boolean CheckLevelUnlocked(int lvl) {return lvl<=player.getMaxUnlockedLevel();}
    public void IncrementMaxLevelUnlocked() {player.setMaxUnlockedLevel(player.getMaxUnlockedLevel()+1);}
    public void ResetPlayerProgress() {player = new Player(3,6,1,0,hs,0,0);System.out.println("PROGRESS HAS BEEN RESET");}
}
