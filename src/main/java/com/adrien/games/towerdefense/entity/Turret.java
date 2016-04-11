package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;

/**
 * Turrets kill enemies !
 */
public class Turret extends Entity {

    private double range;
    private long fireRate;
    private long lastShot;
    private Entity target;

    public Turret(Vector2 position, double range, long fireRate) {
        super(position);
        this.range = range;
        this.fireRate = fireRate;
        this.lastShot = 0L;
        this.target = null;
    }

    @Override
    public void update(Timer timer) {
        if(target != null) {
            lastShot += timer.gelElapsedTime();
            if(lastShot > fireRate) {
                System.out.println("SHOOT");
                lastShot -= fireRate;
            }
        }
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

    public void setTarget(Entity target) {
        this.target = target;
    }
}
