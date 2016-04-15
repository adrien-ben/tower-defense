package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Collision implements Component {

    private int group;

    public Collision(int group) {
        this.group = group;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
