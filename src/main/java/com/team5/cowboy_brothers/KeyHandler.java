package com.team5.cowboy_brothers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class KeyHandler extends KeyAdapter {
    private Player player;
    private GameWorld gameWorld;
    private Timer timer; // Timer to print "hello"

    public KeyHandler(Player player, GameWorld gameWorld) {
        this.player = player;
        this.gameWorld = gameWorld;
        startHelloTimer(); // Start the timer when the KeyHandler is created
    }

    // Method to start a timer that prints "hello" every second
    private void startHelloTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Periodic information: Key handler is running"); // Print "hello" to the console
            }
        }, 0, 10000); // Initial delay of 0 ms, repeat every 1000 ms (1 second)
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                gameWorld.moveObjects(0, player.getMoveSpeed()); // Move objects down
                System.out.println("W");
            }
            case KeyEvent.VK_S -> {
                gameWorld.moveObjects(0, -player.getMoveSpeed()); // Move objects up
                System.out.println("S");
            }
            case KeyEvent.VK_A -> {
                gameWorld.moveObjects(player.getMoveSpeed(), 0); // Move objects right
                System.out.println("A");
            }
            case KeyEvent.VK_D -> {
                gameWorld.moveObjects(-player.getMoveSpeed(), 0); // Move objects left
                System.out.println("D");
            }
        }
    }
}
