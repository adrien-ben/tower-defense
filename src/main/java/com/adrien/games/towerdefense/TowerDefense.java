package com.adrien.games.towerdefense;

import com.adrien.games.application.GameApplication;
import com.adrien.games.application.GameSettings;
import com.adrien.games.application.Input;
import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.towerdefense.entity.Enemy;
import com.adrien.games.towerdefense.level.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the game.
 */
public class TowerDefense extends GameApplication {

    private static final String NAME = "Tower defense";
    private static final String VERSION = "v0.1";
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    private static final long ENEMY_SPAWN_RATE = 2000;

    private Map map = new Map(30, 30, 1D, new Vector2(20, 20), new Vector2(780, 580));
    private List<Enemy> enemies = new ArrayList<>();
    private static long lastSpawnTime = 0;

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
        lastSpawnTime += timer.gelElapsedTime();
        if(lastSpawnTime > ENEMY_SPAWN_RATE) {
            Path path = new Path();
            path.addCheckPoint(new Vector2(map.getMinionSpawn()));
            path.addCheckPoint(new Vector2(map.getObjective()));
            enemies.add(new Enemy(new Vector2(map.getMinionSpawn()), 50, path));
            lastSpawnTime -= ENEMY_SPAWN_RATE;
        }
        enemies.stream().forEach(enemy -> enemy.update(timer));
    }

    @Override
    protected void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillOval((int)map.getMinionSpawn().getX() - 10, (int)map.getMinionSpawn().getY() - 10, 20, 20);
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval((int)map.getObjective().getX() - 10, (int)map.getObjective().getY() - 10, 20, 20);
        graphics2D.setColor(Color.RED);
        enemies.stream().forEach(enemy -> renderEnemy(graphics2D, enemy));
    }

    private void renderEnemy(Graphics2D graphics2D, Enemy enemy) {
        Vector2 position = enemy.getPosition();
        graphics2D.drawOval((int)position.getX() - 5, (int)position.getY() - 5, 10, 10);
    }

    @Override
    protected void dispose() {

    }

    public static void main(String[] args) {
        new TowerDefense().start();
    }
}
