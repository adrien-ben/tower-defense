package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.world.World;
import com.adrien.games.utils.Assert;

/**
 * Entities can be update.
 */
public abstract class Entity {

    protected World world;
    protected Vector2 position;

    public Entity(Vector2 position) {
        this.world = null;
        this.position = Assert.isNotNull(position, "Position cannot be null.");
    }

    public abstract void update(Timer timer);

    public abstract boolean isAlive();

    public Vector2 getPosition() {
        return position;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
