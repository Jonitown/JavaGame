package de.jonitown;

import de.jonitown.grafik.Render;
import de.jonitown.grafik.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Display extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Thread thread;
    private boolean running = false;
    private Render render;
    private Screen screen;
    private Game game;
    private BufferedImage img;
    private int[] pixels;

    public Display() {
        screen = new Screen(WIDTH, HEIGHT);
        game = new Game();
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ( (DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

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
    private void tick() {
        game.tick();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        screen.render(game);
        for (int i = 0;i < WIDTH*HEIGHT;i++) {
            pixels[i] = screen.pixels[i];
        }
        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
        bs.show();

    }
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
