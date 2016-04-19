package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Shoot;
import com.adrien.games.towerdefense.component.Tracker;
import com.adrien.games.towerdefense.entity.EntityFactory;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Generates a bullet if target is in range.
 */
public class TurretSystem extends IteratingSystem {

    private static final float BULLET_SPEED = 200;
    private static final int BULLET_HEALTH = 1;
    private static final float BULLET_SIZE = 10;

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Tracker> trackerMapper = ComponentMapper.getFor(Tracker.class);
    private final ComponentMapper<Shoot> shootMapper = ComponentMapper.getFor(Shoot.class);

    public TurretSystem() {
        super(Family.all(Position.class, Tracker.class, Shoot.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float time) {
        Position position = positionMapper.get(entity);
        Tracker tracker = trackerMapper.get(entity);
        Shoot shoot = shootMapper.get(entity);

        shoot.setSinceLastShot(shoot.getSinceLastShot() + time);

        if(tracker.getEntity() != null && shoot.getSinceLastShot() >= shoot.getRate()) {

            Position targetPosition = positionMapper.get(tracker.getEntity());
            if(targetPosition != null) {

                float distance = new Vector2(targetPosition.getPosition().getX() - position.getPosition().getX(),
                        targetPosition.getPosition().getY() - position.getPosition().getY()
                ).getLength();

                if(distance <= shoot.getRange()) {
                    getEngine().addEntity(EntityFactory.createBullet(
                            new Vector2(position.getPosition()),
                            BULLET_SIZE,
                            BULLET_SPEED,
                            shoot.getDamage(),
                            BULLET_HEALTH,
                            tracker.getEntity()));
                    shoot.setSinceLastShot(0);
                }
            }
        }

    }
}
