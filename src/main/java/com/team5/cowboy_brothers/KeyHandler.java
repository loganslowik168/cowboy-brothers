package com.team5.cowboy_brothers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//import com.team5.cowboy_brothers.GameMaster;

public class KeyHandler extends KeyAdapter {
    Player p;
    GameWorld gw;
    public KeyHandler() {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key pressed: " + e.getKeyChar());
        
        //this isnt the best place for this but putting it in the constructor accesses it too early
        //so this was a quick fix.  feel free to change later if a better solution arises
        p = Cowboy_brothers.olly.player;
        gw = Cowboy_brothers.olly.gameWorld;
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W -> {
                //gameWorld.moveObjects(0, player.getMoveSpeed()); // Move objects down
                //System.out.println("W");
                p.ChangeY(-p.JUMP_HEIGHT);
            }
            case KeyEvent.VK_S -> {
                //gameWorld.moveObjects(0, -player.getMoveSpeed()); // Move objects up
                //System.out.println("S");
                p.ChangeY(p.JUMP_HEIGHT);
                
            }
            case KeyEvent.VK_A -> {
                //gameWorld.moveObjects(player.getMoveSpeed(), 0); // Move objects right
                //System.out.println("A");
                gw.MoveObjects(-Cowboy_brothers.olly.player.GetMoveSpeed());
            }
            case KeyEvent.VK_D -> {
                //gameWorld.moveObjects(-player.getMoveSpeed(), 0); // Move objects left
                //System.out.println("D");
                gw.MoveObjects(Cowboy_brothers.olly.player.GetMoveSpeed());
            }
            case KeyEvent.VK_SPACE->{
                //Call playerBullet creation
                p.fireBullet();
            }
        }
    }
}
