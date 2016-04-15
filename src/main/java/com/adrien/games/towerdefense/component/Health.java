package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Health implements Component {

    private int amount;

    public Health(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
