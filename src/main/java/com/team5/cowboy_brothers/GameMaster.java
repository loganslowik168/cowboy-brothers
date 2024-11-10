/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team5.cowboy_brothers;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
/**
 *
 * @author matth
 */
public class GameMaster {
    
    protected static GameMaster olly;
    int c=0;
    
    
    
    public GameMaster(){
       Cowboy_bros_Menu n1 =  new Cowboy_bros_Menu();
    }
    
    public static void main(String[] args) {
        
        
        olly = new GameMaster();
        
        System.out.println("Creating test drawer");
                // Get all open frames (Frame instances)
        Frame[] frames = JFrame.getFrames();

        // Loop through the frames array and check for the first instance of JFrame
        for (Frame frame : frames) {
            if (frame instanceof JFrame) {  // Check if the frame is actually a JFrame
                JFrame activeFrame = (JFrame) frame; // Safely cast it to JFrame
                System.out.println("Active JFrame found: " + activeFrame.getTitle());
                
                // Pass the active JFrame to TestSpriteDrawer
                var tsd = new TestSpriteDrawer(activeFrame); // Pass the active frame to the TestSpriteDrawer
            }
        }

        final int del=1000;
        Timer tL1=new Timer(del, null);
        tL1.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent evt){
            if(olly.c==10){tL1.stop();}else{olly.increm(); System.out.println("C is "+olly.c);}
        }
        });
        tL1.start();
        
        
        
    }
    
    public void increm(){
        c++;
    }
    //time
    public void time(){
     
    }
}
