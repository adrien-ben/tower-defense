package com.adrien.games.towerdefense.level;

import com.adrien.games.application.Timer;
import com.adrien.games.towerdefense.entity.EnemyGenerator;
import com.adrien.games.towerdefense.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains the map, the entities and the enemy generators.
 */
public class Level {

    private Map map;
    private List<EnemyGenerator> enemyGenerators;
    private List<Entity> entities;

    public Level(Map map) {
        this.map = map;
        this.enemyGenerators = new ArrayList<>();
        enemyGenerators.addAll(map.getPaths().stream().map(path -> new EnemyGenerator(
                path.getCheckPoints().getFirst(), 2, 1, 5, path
        )).collect(Collectors.toList()));
    }

    /**
     * Updates enemy generators and retrieve generated enemies.
     * Then updates all entities.
     * @param timer A timer.
     */
    public void update(Timer timer) {
        enemyGenerators.stream().forEach(enemyGenerator ->  {
            enemyGenerator.update(timer);
            if(enemyGenerator.hasPendingEnemies()) {
                entities.addAll(enemyGenerator.getEnemies());
            }
        });
        entities.stream().forEach(entity -> entity.update(timer));
    }

}
