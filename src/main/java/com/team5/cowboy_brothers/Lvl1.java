package com.team5.cowboy_brothers;
import javax.swing.*;
import java.util.*;
import java.awt.*;
       
public class Lvl1 extends Map {
    Enemy enemy1;
    
    public Lvl1() {
        super(800, 600); // Example dimensions
        loadMapStats();
        enemy1 = new Enemy("Kyle",1,Color.black, Cowboy_brothers.olly.VisibleMenu.gameplayPanel);
        Cowboy_brothers.olly.VisibleMenu.gameplayPanel.setEnemy(enemy1);
    }

    @Override
    public void loadMapStats() {
        // Load level 1 map statistics here
        // This might involve setting up terrain, enemies, etc.
        System.out.println("Loading Level 1 map stats...");
        
    }

    @Override
    public double[] getStartingPosition() {
        return new double[] {100, 100}; // Example starting position
    }
}
