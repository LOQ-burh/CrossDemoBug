package com.demo.mainapp.crossyroaddemo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteCustom extends ImageView {
    // Sprite location.
    private double xloc, yloc;
    // Sprite direction.
    private double xdir, ydir;

    /** * The default constructor. */
    public SpriteCustom() {
        super();
        xloc = 0;
        yloc = 0;
        xdir = 0;
        ydir = 0;
    }

    /** * Constructor that sets the sprite image and location.*/
    public SpriteCustom(String filename, int xloc, int yloc) {
        super(new Image(SpriteCustom.class.getResourceAsStream(filename)));
        this.xloc = xloc;
        this.yloc = yloc;
        setX(xloc);
        setY(yloc);
    }

    /** * Constructor that takes the location as the argument. */
    public SpriteCustom(int xloc, int yloc) {
        this.xloc = xloc;
        this.yloc = yloc;
        setX(xloc);
        setY(yloc);
    }

    /** * Constructor that takes an image filename as the argument. */
    public SpriteCustom(String filename) {
        super(new Image(SpriteCustom.class.getResourceAsStream(filename)));
    }

    /* * Getters. */
    // Get xloc.
    public int getXLoc() {
        return (int) xloc;
    }

    /* * Setters */
    // Sets xloc.
    public void setXLoc(int xloc) {
        this.xloc = xloc;
        setX(xloc);
    }

    // Get yloc.
    public int getYLoc() {
        return (int) yloc;
    }

    // Sets yloc.
    public void setYLoc(int yloc) {
        this.yloc = yloc;
        setY(yloc);
    }

    // Get xdir.
    public double getXDir() {
        return xdir;
    }

    // Sets xdir.
    public void setXDir(double xdir) {
        this.xdir = xdir;
    }

    // Get ydir.
    public double getYDir() {
        return ydir;
    }

    // Sets ydir.
    public void setYDir(double ydir) {
        this.ydir = ydir;
    }

    /*
     * Moves character by adding the direction to the location.
     */
    public void move() {
        xloc += xdir;
        yloc += ydir;
        setX(xloc);
        setY(yloc);
    }

    // Return the width of the sprite.
    public int getWidth() {
        return (int) getImage().getWidth();
    }

    // Return the height of the sprite.
    public int getHeight() {
        return (int) getImage().getHeight();
    }

    public void setImage(String filename) {
        super.setImage(new Image(SpriteCustom.class.getResourceAsStream(filename)));
    }
}
