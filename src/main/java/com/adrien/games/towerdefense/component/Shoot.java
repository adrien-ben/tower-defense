package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Shoot implements Component {

    private float rate;
    private int damage;
    private float range;

    public Shoot(float rate, int damage, float range) {
        this.rate = rate;
        this.damage = damage;
        this.range = range;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }
}
