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

    private static final float BULLET_SPEED = 100;
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

        shoot.sinceLastShot += time;

        if(tracker.entity != null && shoot.sinceLastShot >= shoot.rate) {

            Position targetPosition = positionMapper.get(tracker.entity);
            if(targetPosition != null) {

                float distance = new Vector2(targetPosition.position.getX() - position.position.getX(),
                        targetPosition.position.getY() - position.position.getY()
                ).getLength();

                if(distance <= shoot.range) {
                    getEngine().addEntity(EntityFactory.createBullet(
                            new Vector2(position.position),
                            BULLET_SIZE,
                            BULLET_SPEED,
                            shoot.damage,
                            BULLET_HEALTH,
                            tracker.entity));
                    shoot.sinceLastShot = 0;
                }
            }
        }

    }
}
