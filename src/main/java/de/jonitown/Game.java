package de.jonitown;

import de.jonitown.input.Controller;

import java.awt.event.KeyEvent;

public class Game {
    public int time;
    public Controller controller;

    public Game() {
        controller = new Controller();
    }

    public void tick(boolean[] keys) {
        time++;
        boolean forward = keys[KeyEvent.VK_W];
        boolean backward = keys[KeyEvent.VK_S];
        boolean left = keys[KeyEvent.VK_A];
        boolean right = keys[KeyEvent.VK_D];
        boolean turnLeft = keys[KeyEvent.VK_LEFT];
        boolean turnRight = keys[KeyEvent.VK_RIGHT];

        controller.tick(forward, backward, left, right, turnLeft, turnRight);
    }

}
