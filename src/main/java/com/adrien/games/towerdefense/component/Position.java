package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

public class Position implements Component {

    private Vector2 position;

    public Position(Vector2 position) {
        this.position = position;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
