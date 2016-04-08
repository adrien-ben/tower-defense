package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.utils.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates enemies from time to time.
 */
public class EnemyGenerator implements Entity {

    private Vector2 position;
    private long rate;
    private long timeSinceLastGeneration;
    private int numberToGenerate;
    private List<Enemy> enemies;
    private double enemySpeed;
    private Path enemyPath;

    public EnemyGenerator(Vector2 position, long rate, int numberToGenerate, double enemySpeed, Path enemyPath) {
        this.position = Assert.isNotNull(position, "position cannot be null");
        this.rate = rate;
        this.timeSinceLastGeneration = 0;
        this.numberToGenerate = numberToGenerate;
        this.enemies = new ArrayList<>();
        this.enemySpeed = enemySpeed;
        this.enemyPath = Assert.isNotNull(enemyPath, "Path cannot be null");
    }

    @Override
    public void update(Timer timer) {
        timeSinceLastGeneration += timer.gelElapsedTime();
        if(timeSinceLastGeneration > rate) {
            generateEnemies();
            timeSinceLastGeneration -= rate;
        }
    }

    private void generateEnemies() {
        for(int i = 0; i < numberToGenerate; i++) {
            enemies.add(new Enemy(new Vector2(position), enemySpeed, enemyPath));
        }
    }

    public boolean hasPendingEnemies() {
        return enemies.size() > 0;
    }

    public List<Enemy> getEnemies() {
        List<Enemy> result = new ArrayList<>(enemies);
        enemies.clear();
        return result;
    }
}
