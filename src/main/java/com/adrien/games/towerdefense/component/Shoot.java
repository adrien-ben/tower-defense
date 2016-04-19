package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Shoot implements Component {

    public float rate;
    public int damage;
    public float range;
    public float sinceLastShot;

    public Shoot(float rate, int damage, float range) {
        this.rate = rate;
        this.damage = damage;
        this.range = range;
        this.sinceLastShot = rate;
    }

}
