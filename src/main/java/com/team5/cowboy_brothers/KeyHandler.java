package com.team5.cowboy_brothers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
    private Player player;

    public KeyHandler(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: // Move up
                player.setY(player.getPosition()[1] - player.getMoveSpeed());
                break;
            case KeyEvent.VK_S: // Move down
                player.setY(player.getPosition()[1] + player.getMoveSpeed());
                break;
            case KeyEvent.VK_A: // Move left
                player.setX(player.getPosition()[0] - player.getMoveSpeed());
                break;
            case KeyEvent.VK_D: // Move right
                player.setX(player.getPosition()[0] + player.getMoveSpeed());
                break;
        }
    }
}
