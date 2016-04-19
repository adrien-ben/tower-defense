package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Health implements Component {

    public int amount;

    public Health(int amount) {
        this.amount = amount;
    }

}
