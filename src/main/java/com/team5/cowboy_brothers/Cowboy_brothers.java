package com.team5.cowboy_brothers;

import javax.swing.*;

public class Cowboy_brothers extends JFrame {
    static GameMaster olly;
    public Cowboy_brothers() {
        
    }
    //Main
    public static void main(String[] args) { // main function
        System.out.println("-- Begin program execution --");
        if (olly == null) olly = new GameMaster();
        System.out.println(olly.player);
    }
}
