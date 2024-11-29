package com.team5.cowboy_brothers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Flag extends GameObject {

    Timer repaintTimer;
    private GamePanel targetPanel;
    public Flag(int x, int y, GamePanel targetPanel) {
        super(x, y, "sprites/Flag.png",targetPanel);
        targetPanel.setFlag(this);
        //Cowboy_brothers.olly.gameWorld.objects.add(this);
    }

        
}
