package com.adrien.games.towerdefense.player;

public class Player {

    private int money;

    public Player() {
        this.money = 100;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }

    public void removeMoney(int amount) {
        addMoney(-amount);
    }

    public int getMoney() {
        return money;
    }
}
