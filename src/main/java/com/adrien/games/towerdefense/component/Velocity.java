package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

public class Velocity implements Component {

    public float speed;
    public Vector2 direction;

    public Velocity(float speed, Vector2 direction) {
        this.speed = speed;
        this.direction = direction;
    }

}
