package com.adrien.games.towerdefense.component;

import com.badlogic.ashley.core.Component;

public class Player implements Component {

    private int money;

    public Player(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
