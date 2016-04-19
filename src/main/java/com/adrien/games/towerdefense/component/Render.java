package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

import java.awt.*;

public class Render implements Component {

    public Color color;
    public boolean filled;

    public Render(Color color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

}
