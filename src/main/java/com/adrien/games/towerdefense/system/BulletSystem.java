package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Tracker;
import com.adrien.games.towerdefense.component.Velocity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Modifies the velocity of the entity according to its target.
 */
public class BulletSystem extends IteratingSystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Velocity> velocityMapper = ComponentMapper.getFor(Velocity.class);
    private final ComponentMapper<Tracker> trackerMapper = ComponentMapper.getFor(Tracker.class);

    public BulletSystem() {
        super(Family.all(Position.class, Velocity.class, Tracker.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float time) {
        Position position = positionMapper.get(entity);
        Velocity velocity = velocityMapper.get(entity);
        Tracker tracker = trackerMapper.get(entity);

        if(tracker.getEntity() != null) {
            Position trackerPosition = positionMapper.get(tracker.getEntity());
            if(trackerPosition != null) {
                Vector2 direction = new Vector2(trackerPosition.getPosition().getX() - position.getPosition().getX(),
                        trackerPosition.getPosition().getY() - position.getPosition().getY());
                direction.normalize();
                direction.scale(time*velocity.getSpeed());
                velocity.setDirection(direction);
            }
        } else {
            getEngine().removeEntity(entity);
        }

    }
}
