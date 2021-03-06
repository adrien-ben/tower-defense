package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Spawner;
import com.adrien.games.towerdefense.entity.EntityFactory;
import com.adrien.games.towerdefense.level.Level;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Spawn minions.
 */
public class SpawnSystem extends IteratingSystem {

    private static final float MINION_SIZE = 12;
    private static final float MINION_SPEED = 50;
    private static final int MINION_HEALTH = 4;

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Spawner> spawnerMapper = ComponentMapper.getFor(Spawner.class);
    private Level level;

    public SpawnSystem(Level level) {
        super(Family.all(Position.class, Spawner.class).get());
        this.level = level;
    }

    @Override
    protected void processEntity(Entity entity, float time) {

        Position position = positionMapper.get(entity);
        Spawner spawner = spawnerMapper.get(entity);

        spawner.sinceLastSpawn += time;

        if(spawner.sinceLastSpawn >= spawner.rate) {

            getEngine().addEntity(EntityFactory.createMinion(
                    new Vector2(position.position),
                    MINION_SIZE,
                    MINION_SPEED,
                    level.getPath(position.position, level.getObjective()),
                    MINION_HEALTH
            ));
            spawner.sinceLastSpawn = 0;
        }
    }
}
