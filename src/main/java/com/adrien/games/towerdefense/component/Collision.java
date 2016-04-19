package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Collision implements Component {

    public int group;

    public Collision(int group) {
        this.group = group;
    }

}
