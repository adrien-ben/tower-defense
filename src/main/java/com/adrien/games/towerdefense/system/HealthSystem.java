package com.adrien.games.towerdefense.system;

import com.adrien.games.towerdefense.component.Health;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Removes dead entities.
 */
public class HealthSystem extends IteratingSystem {

    private final ComponentMapper<Health> healthMapper = ComponentMapper.getFor(Health.class);

    public HealthSystem() {
        super(Family.all(Health.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float time) {
        Health health = healthMapper.get(entity);
        if(health.getAmount() <= 0) {
            getEngine().removeEntity(entity);
        }
    }
}
