package com.team5.cowboy_brothers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class KeyHandler extends KeyAdapter {
    Player p;
    GameWorld gw;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    
    // Timer to control the update interval
    private Timer updateTimer;

    // Timer interval (e.g., 16 ms ~ 60 FPS)
    private static final int UPDATE_INTERVAL = 16;

    public KeyHandler() {
        // Initialize the timer that calls the update method at regular intervals
        updateTimer = new Timer(UPDATE_INTERVAL, e -> update());
        updateTimer.start(); // Start the timer
    }

    @Override
    public void keyPressed(KeyEvent e) {
        p = Cowboy_brothers.olly.player;
        gw = Cowboy_brothers.olly.gameWorld;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                //p.ChangeY(-p.JUMP_HEIGHT); // Handle jump
                
                    p.AttemptJump();
            }
            case KeyEvent.VK_S -> {
                //p.ChangeY(p.JUMP_HEIGHT); // Handle crouch
            }
            case KeyEvent.VK_A -> {
                if (!movingLeft) {
                    movingLeft = true; // Start moving left
                    Cowboy_brothers.olly.player.CheckForDirectionChange(-1);
                }
            }
            case KeyEvent.VK_D -> {
                if (!movingRight) {
                    movingRight = true; // Start moving right
                    Cowboy_brothers.olly.player.CheckForDirectionChange(1);
                }
            }
            case KeyEvent.VK_SPACE -> {
                p.fireBullet(); // Fire bullet
                //Cowboy_brothers.olly.VisibleMenu.gameplayPanel.bbs.clear();
                /*for (GameObject obj : Cowboy_brothers.olly.gameWorld.objects) {
                    System.out.println(obj);
                }*/
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> {
                movingLeft = false; // Stop moving left when key is released
                if(movingRight)Cowboy_brothers.olly.player.CheckForDirectionChange(1);
            }
            case KeyEvent.VK_D -> {
                movingRight = false; // Stop moving right when key is released
                if(movingLeft)Cowboy_brothers.olly.player.CheckForDirectionChange(-1);
            }
        }
    }

    public void update() {
        // Smoothly move the player based on whether keys are being held down
        if (!Cowboy_brothers.olly.gameplayPanel.hasFocus()) {return;}
        if (movingLeft) {
            gw.MoveObjects(Cowboy_brothers.olly.player.GetMoveSpeed()); // Move left continuously
        }
        if (movingRight) {
            gw.MoveObjects(-Cowboy_brothers.olly.player.GetMoveSpeed()); // Move right continuously
        }

    }
    public void MenuRelease(){
        movingLeft = false;
        movingRight = false;
    }
}
