package com.team5.cowboy_brothers;

import javax.swing.*;

public class Cowboy_brothers extends JFrame {
    static GameMaster olly;
    
    public Cowboy_brothers() {
        olly = new GameMaster();
    }
    //Main
    public static void main(String[] args) { // main function
        System.out.println("-- Begin program execution --");
        new Cowboy_brothers();
        //olly.player.ShouldGravitate = false; //change this later in order to reapply gravity
    }
}
