package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class Tracker implements Component {

    private float range;
    private Entity entity;

    public Tracker(float range, Entity entity) {
        this.range = range;
        this.entity = entity;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
