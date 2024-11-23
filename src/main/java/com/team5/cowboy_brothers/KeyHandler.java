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
                //System.out.println("W");
                Cowboy_brothers.olly.player.setPosition(40,40);
            }
            case KeyEvent.VK_S -> {
                //gameWorld.moveObjects(0, -player.getMoveSpeed()); // Move objects up
                //System.out.println("S");
            }
            case KeyEvent.VK_A -> {
                //gameWorld.moveObjects(player.getMoveSpeed(), 0); // Move objects right
                //System.out.println("A");
                Cowboy_brothers.olly.gameWorld.MoveObjects(-Cowboy_brothers.olly.player.GetMoveSpeed());
            }
            case KeyEvent.VK_D -> {
                //gameWorld.moveObjects(-player.getMoveSpeed(), 0); // Move objects left
                //System.out.println("D");
                Cowboy_brothers.olly.gameWorld.MoveObjects(Cowboy_brothers.olly.player.GetMoveSpeed());
            }
            case KeyEvent.VK_SPACE->{
                //Call playerBullet creation
                Cowboy_brothers.olly.player.fireBullet();
            }
        }
    }
}
