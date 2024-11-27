package com.team5.cowboy_brothers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//import com.team5.cowboy_brothers.GameMaster;

public class KeyHandler extends KeyAdapter {

    public KeyHandler() {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key pressed: " + e.getKeyChar());
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                //gameWorld.moveObjects(0, player.getMoveSpeed()); // Move objects down
                Cowboy_brothers.olly.player.setPosition(40,40);
            }
            case KeyEvent.VK_S -> {
                //gameWorld.moveObjects(0, -player.getMoveSpeed()); // Move objects up
            }
            case KeyEvent.VK_A -> {
                Cowboy_brothers.olly.player.changeDirection(-1); // Face left
                Cowboy_brothers.olly.player.update(); // want to move player
                //gameWorld.moveObjects(player.getMoveSpeed(), 0); // Move objects right
            }
            case KeyEvent.VK_D -> {
                Cowboy_brothers.olly.player.changeDirection(1); // Face right
                Cowboy_brothers.olly.player.update(); // want to move player
                //gameWorld.moveObjects(-player.getMoveSpeed(), 0); // Move objects left
            }
            case KeyEvent.VK_SPACE->{
                //Call playerBullet creation
                Cowboy_brothers.olly.player.fireBullet();
            }
        }
    }
}
