package com.demo.mainapp.crossyroaddemo;//Class to draw graphics.

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
/*
 * Class that creates a sprite with an image, location,
 * directional movement, and a collision method.
 */
class Sprite {
	private ImageView imageView;
	// Sprite location.
	private double xloc, yloc;
	// Sprite direction.
	private double xdir, ydir;
	// Holds the image of the sprite.
	private Image image;
	// Draw sprite image or not.
	private boolean show = true;
	// Holds the image filename.
	private String filename = "";

	/** * The default constructor. */
	Sprite() {
		image = null;
		xloc = 0;
		yloc = 0;
		xdir = 0;
		ydir = 0;
		imageView = new ImageView();
	}

	/** * Constructor that sets the sprite image and location.*/
	public Sprite(String filename, int xloc, int yloc) {
		setImage(filename);
		this.xloc = xloc;
		this.yloc = yloc;
	}
	/** * Constructor that takes the location as the argument. */
	public Sprite(int xloc, int yloc) {
		this.xloc = xloc;
		this.yloc = yloc;
	}
	/** * Constructor that takes an image filename as the argument. */
	Sprite(String filename) {
		setImage(filename);
	}

	/** * Method to set the image variable. */
	void setImage(String filename) {
		this.filename = filename;

		try {
			this.image = new Image(getClass().getResourceAsStream(filename));
			this.imageView.setImage(this.image);
		} catch (Exception e) {
			image = null;
		}
	}

	/* * Getters. */
	// Get xloc.
	int getXLoc() {
		return (int) xloc;
	}
	/* * Setters */
	// Sets xloc.
	void setXLoc(int xloc) {
		this.xloc = xloc;
		this.imageView.setX(xloc); //
	}

	// Get yloc.
	int getYLoc() {
		return (int) yloc;
	}

	// Sets yloc.
	void setYLoc(int yloc) {
		this.yloc = yloc;
		this.imageView.setY(yloc); //
	}

	// Get xdir.
	public double getXDir() {
		return xdir;
	}

	// Sets xdir.
	void setXDir(double xdir) {
		this.xdir = xdir;
	}

	// Get ydir.
	public double getYDir() {
		return ydir;
	}

	// Sets ydir.
	void setYDir(double ydir) {
		this.ydir = ydir;
	}

	// Get image filename.
	String getFileName() {
		return filename;
	}

	/*
	 * Moves character by adding the direction to the location.
	 */
	void move() {
		xloc += xdir;
		yloc += ydir;
		imageView.setX(xloc);
		imageView.setY(yloc);
	}

	// Return the width of the sprite or 20 if the image is null.
	int getWidth() {
		if (image == null)
			return 20;
		else
			return (int) image.getWidth();
	}

	// Return the height of the sprite or 20 if the image in null.
	int getHeight() {
		if (image == null)
			return 20;
		else
			return (int) image.getHeight();
	}
	public ImageView getImageView() {
		return imageView;
	}
	/*
	 * Method to draw sprite onto Canvas.
	 */
	void paint(GraphicsContext gc) {
		if (show) {
			if (image == null) {
				gc.setFill(Color.BLACK);
				gc.fillRect(xloc, yloc, 50, 50);
			} else {
				gc.drawImage(image, xloc, yloc);
			}
		}
	}
}
