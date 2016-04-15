package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

import java.awt.*;

public class Render implements Component {

    private Shape shape;
    private Color color;

    public Render(Shape shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
