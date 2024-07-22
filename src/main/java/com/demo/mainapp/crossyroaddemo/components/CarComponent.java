package com.demo.mainapp.crossyroaddemo.components;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class CarComponent extends Rectangle {
    private double speed;
    private static final double MIN_SPEED = 1.0;
    private static final double MAX_SPEED = 4.0;

    public CarComponent(double x, double y) {
        super(50, 50, Color.RED);
        setX(x);
        setY(y);

        // Random speed
        Random random = new Random();
        speed = MIN_SPEED + (MAX_SPEED - MIN_SPEED) * random.nextDouble();
    }

    public void move() {
        setX(getX() + speed); // Di chuyển obstacle sang phải

        // Nếu obstacle ra khỏi màn hình bên phải, di chuyển lại về bên trái màn hình
        if (getX() > 640) {
            setX(-getWidth());
            Random random = new Random();
            speed = MIN_SPEED + (MAX_SPEED - MIN_SPEED) * random.nextDouble();
        }
    }
}
