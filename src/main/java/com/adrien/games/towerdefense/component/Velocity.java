package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

public class Velocity implements Component {

    private float speed;
    private Vector2 direction;

    public Velocity(float speed, Vector2 direction) {
        this.speed = speed;
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }
}
