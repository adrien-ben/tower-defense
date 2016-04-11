package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;

import java.util.List;
import java.util.Optional;

/**
 * Turrets kill enemies !
 */
public class Turret extends Entity {

    private double range;
    private long fireRate;
    private long lastShot;
    private List<Entity> potentialTargets;
    private Entity currentTarget;

    public Turret(Vector2 position, double range, long fireRate, List<Entity> potentialTargets) {
        super(position);
        this.range = range;
        this.fireRate = fireRate;
        this.lastShot = 0L;
        this.potentialTargets = potentialTargets;
        this.currentTarget = null;
    }

    @Override
    public void update(Timer timer) {
        Entity target = hasTarget() ? currentTarget : getTarget();
        if(target != null) {
            lastShot += timer.gelElapsedTime();
            if(lastShot > fireRate) {
                System.out.println("SHOT AT " + target.getPosition().getX() + " " + target.getPosition().getY());
                lastShot -= fireRate;
            }
        }
    }

    private boolean hasTarget() {
        return currentTarget != null && getDistance(currentTarget.getPosition()) < range;
    }

    private Entity getTarget() {
        Optional<Entity> first = potentialTargets.stream()
                .filter(entity -> getDistance(entity.getPosition()) < range)
                .findFirst();
        if(first.isPresent()) {
            return first.get();
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
