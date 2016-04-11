package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.utils.Assert;

/**
 * An enemy follows its path.
 */
public class Enemy extends Entity {

    private double speed;
    private final Path path;

    public Enemy(Vector2 position, double speed, Path path) {
        super(position);
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
