package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

import java.awt.*;

public class Render implements Component {

    private Color color;
    private boolean filled;

    public Render(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
