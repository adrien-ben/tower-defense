package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.utils.Assert;

/**
 * Entities can be update.
 */
public abstract class Entity {

    protected Vector2 position;

    public Entity(Vector2 position) {
        this.position = Assert.isNotNull(position, "Position cannot be null.");
    }

    public abstract void update(Timer timer);

    public Vector2 getPosition() {
        return position;
    }

    public abstract boolean isAlive();

}
