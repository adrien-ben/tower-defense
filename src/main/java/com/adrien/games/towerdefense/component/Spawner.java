package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Spawner implements Component {

    public float rate;
    public float sinceLastSpawn;

    public Spawner(float rate) {
        this.rate = rate;
        this.sinceLastSpawn = rate;
    }

}
