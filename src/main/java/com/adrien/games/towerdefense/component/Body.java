package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Body implements Component {

    public float size;

    public Body(float size) {
        this.size = size;
    }

}
