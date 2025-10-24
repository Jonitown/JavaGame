package de.jonitown;

import javax.swing.*;
import java.awt.*;

public class Display extends Canvas implements Runnable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Thread thread;
    private boolean running = false;

    public void start() {
        if(running) {return;}
        running = true;
        thread = new Thread(this);
        thread.start();

        System.out.println("Display started");

    }
    public void stop() {
        if(!running) {return;}
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public void run() {
        while(running) {
            tick();
            render();
        }
    }
    private void tick() {}
    private void render() {}
    public static void main(String[] args) {
        JFrame frame = new JFrame("Main Game");
        Display mainGame = new Display();

        frame.add(mainGame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(WIDTH,HEIGHT);
        frame.setLocationRelativeTo(null);

        mainGame.start();
    }
}
