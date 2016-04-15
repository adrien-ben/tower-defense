package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Spawner implements Component {

    private float rate;

    public Spawner(float rate) {
        this.rate = rate;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
