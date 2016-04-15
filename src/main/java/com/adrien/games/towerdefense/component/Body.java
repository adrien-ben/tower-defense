package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Body implements Component {

    private float size;

    public Body(float size) {
        this.size = size;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
}
