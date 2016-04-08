package com.adrien.games.towerdefense;

import com.adrien.games.application.GameApplication;
import com.adrien.games.application.GameSettings;
import com.adrien.games.application.Input;
import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;

import java.awt.*;

/**
 * Main class of the game.
 */
public class TowerDefense extends GameApplication {

    private static final String NAME = "Tower defense";
    private static final String VERSION = "v0.1";
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    @Override
    protected void init(GameSettings gameSettings) {
        gameSettings.setName(NAME + " " + VERSION);
        gameSettings.setWidth(SCREEN_WIDTH);
        gameSettings.setHeight(SCREEN_HEIGHT);
    }

    @Override
    protected void handleInput(Input input) {

    }

    @Override
    protected void update(Timer timer) {

    }

    @Override
    protected void render(Graphics2D graphics2D) {

    }

    @Override
    protected void dispose() {

    }

    public static void main(String[] args) {
        new TowerDefense().start();
    }
}
