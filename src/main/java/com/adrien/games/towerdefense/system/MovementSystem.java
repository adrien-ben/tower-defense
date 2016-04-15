package com.adrien.games.towerdefense.system;

import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Velocity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Add velocity to position.
 */
public class MovementSystem extends IteratingSystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Velocity> velocityMapper = ComponentMapper.getFor(Velocity.class);

    public MovementSystem() {
        super(Family.all(Position.class, Velocity.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float time) {
        Position position = positionMapper.get(entity);
        Velocity velocity = velocityMapper.get(entity);
        position.getPosition().add(velocity.getDirection());
    }

}
