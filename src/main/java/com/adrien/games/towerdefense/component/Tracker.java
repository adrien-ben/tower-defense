package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class Tracker implements Component {

    public float range;
    public Entity entity;

    public Tracker(float range, Entity entity) {
        this.range = range;
        this.entity = entity;
    }

}
