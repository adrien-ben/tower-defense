package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Damage implements Component {

    public int amount;

    public Damage(int amount) {
        this.amount = amount;
    }

}
