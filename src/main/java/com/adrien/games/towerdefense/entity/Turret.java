package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;

import java.util.Optional;

/**
 * Turrets kill enemies !
 */
public class Turret extends Entity {

    private double range;
    private long fireRate;
    private long lastShot;
    private Enemy currentTarget;

    public Turret(Vector2 position, double range, long fireRate) {
        super(position);
        this.range = range;
        this.fireRate = fireRate;
        this.lastShot = 0L;
        this.currentTarget = null;
    }

    @Override
    public void update(Timer timer) {
        Enemy target = hasTarget() ? currentTarget : getTarget();
        if(target != null) {
            lastShot += timer.gelElapsedTime();
            if(lastShot > fireRate) {
                world.addEntity(new Bullet(new Vector2(position), 40, 1, target));
                lastShot -= fireRate;
            }
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    private boolean hasTarget() {
        return currentTarget != null && getDistance(currentTarget.getPosition()) < range;
    }

    private Enemy getTarget() {
        Optional<Entity> first = world.getEntities(Enemy.class).stream()
                .filter(entity -> getDistance(entity.getPosition()) < range)
                .findFirst();
        if(first.isPresent()) {
            return (Enemy)first.get();
        }
        return null;
    }

    private double getDistance(Vector2 position) {
        double xDistance = position.getX() - getPosition().getX();
        double yDistance = position.getY() - getPosition().getY();
        return Math.sqrt(xDistance*xDistance + yDistance*yDistance);
    }

    public double getRange() {
        return range;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setFireRate(long fireRate) {
        this.fireRate = fireRate;
    }
}
