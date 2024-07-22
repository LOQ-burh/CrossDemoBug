package com.demo.mainapp.crossyroaddemo;

import javafx.scene.input.KeyCode;

public class HeroManager {
    private Sprite hero;
    private int up, down, left, right;
    private boolean press, invincibility;
    private int invDuration, invTimeLeft;

    public HeroManager(Sprite hero) {
        this.hero = hero;
        this.up = 0;
        this.down = 0;
        this.left = 0;
        this.right = 0;
        this.press = false;
        this.invincibility = false;
        this.invDuration = 0;
        this.invTimeLeft = 0;
    }

    public void handleKeyPress(KeyCode code) {
        switch (code) {
            case UP -> up = 1;
            case DOWN -> down = 1;
            case LEFT -> left = 1;
            case RIGHT -> right = 1;
            case CONTROL -> invincibility = true;
//            case ENTER -> Display.newGame();
            case SHIFT -> {
                if (press) {
                    press = false;
                    hero.setXDir(0);
                    hero.setYDir(0);
                }
            }
        }
        press = true;
    }

    public void handleKeyRelease(KeyCode code) {
        switch (code) {
            case UP -> up = 0;
            case DOWN -> down = 0;
            case LEFT -> left = 0;
            case RIGHT -> right = 0;
        }
    }

    public void updateHero() {
        // Update hero movement and invincibility logic
        // Similar to jumpHero() and heroBounds() methods
    }
}

