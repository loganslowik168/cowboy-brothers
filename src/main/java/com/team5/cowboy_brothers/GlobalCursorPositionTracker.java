package com.team5.cowboy_brothers;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GlobalCursorPositionTracker {
    private Timer timer; // Timer to print position periodically

    public GlobalCursorPositionTracker() {
        // Start the timer to print cursor position every second
        startPositionTimer();
    }

    private void startPositionTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Point cursorLocation = MouseInfo.getPointerInfo().getLocation();
                System.out.println("Cursor Position: (" + cursorLocation.x + ", " + cursorLocation.y + ")");
            }
        }, 0, 1000); // Print position every second
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GlobalCursorPositionTracker::new);
    }
}
