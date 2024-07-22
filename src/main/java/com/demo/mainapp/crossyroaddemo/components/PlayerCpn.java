package com.demo.mainapp.crossyroaddemo.components;

import com.almasb.fxgl.entity.component.Component;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayerCpn extends Component {
    private Rectangle shape;
    private double speed = 5.0;

    public PlayerCpn(double x, double y) {
        shape = new Rectangle(x, y, 30, 30);
        shape.setFill(Color.BLUE);
    }

    public Rectangle getShape() {
        return shape;
    }

    public void moveUp() {
        shape.setY(shape.getY() - speed);
    }

    public void moveDown() {
        shape.setY(shape.getY() + speed);
    }

    public void moveLeft() {
        shape.setX(shape.getX() - speed);
    }

    public void moveRight() {
        shape.setX(shape.getX() + speed);
    }
}
