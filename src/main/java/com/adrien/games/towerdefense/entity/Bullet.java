package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;

/**
 * Bullets follow their target until it touches it.
 */
public class Bullet extends Entity {

    private double speed;
    private int damage;
    private Enemy target;
    private boolean hit;

    public Bullet(Vector2 position, double speed, int damage, Enemy target) {
        super(position);
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.hit = false;
    }

    @Override
    public void update(Timer timer) {
        Vector2 direction = new Vector2(target.getPosition().getX() - position.getX(), target.getPosition().getY() - position.getY());
        double distance = direction.getLength();
        direction.normalize();
        direction.scale(speed*timer.gelElapsedTime()/Timer.MS_PER_SECOND);
        if(distance < direction.getLength()) {
            target.hit(damage);
            hit = true;
        } else {
            position.add(direction);
        }
    }

    @Override
    public boolean isAlive() {
        return !hit;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }
}
