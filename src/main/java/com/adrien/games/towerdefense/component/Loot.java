package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Loot implements Component {

    public int money;

    public Loot(int money) {
        this.money = money;
    }

}
