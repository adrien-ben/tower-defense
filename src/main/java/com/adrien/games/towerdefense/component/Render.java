package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

import java.awt.*;

public class Render implements Component {

    private Color color;

    public Render(Shape shape, Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
