package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.utils.Assert;

/**
 * Enemy class.
 */
public class Enemy implements Entity {

    private Vector2 position;
    private double speed;
    private final Path path;

    public Enemy(Vector2 position, double speed, Path path) {
        this.position = Assert.isNotNull(position, "Position cannot be null.");
        this.speed = speed;
        this.path = Assert.isNotNull(path, "Path cannot be null.");
    }

    @Override
    public void update(Timer timer) {
        if(!path.isEndReached()) {
            path.update(speed, (double)timer.gelElapsedTime()/Timer.MS_IN_ONE_S);
            position = path.getPosition();
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public Path getPath() {
        return path;
    }

}
