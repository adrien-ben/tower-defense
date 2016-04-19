package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Body;
import com.adrien.games.towerdefense.component.Path;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Velocity;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

/**
 * Updates minions velocity.
 */
public class MinionSystem extends IteratingSystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Velocity> velocityMapper = ComponentMapper.getFor(Velocity.class);
    private final ComponentMapper<Path> pathMapper = ComponentMapper.getFor(Path.class);

    public MinionSystem() {
        super(Family.all(Position.class, Body.class, Path.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float time) {
        Path path = pathMapper.get(entity);
        Velocity velocity = velocityMapper.get(entity);
        int nextNodeIndex = path.next;

        if(nextNodeIndex < path.positions.size()) {
            Position position = positionMapper.get(entity);

            Vector2 nextNode = path.positions.get(nextNodeIndex);
            Vector2 direction = new Vector2(nextNode.getX() - position.position.getX(),
                    nextNode.getY() - position.position.getY());
            Vector2 velocityVector = getVelocityVector(direction, time, velocity.speed);
            if(direction.getLength() <= velocityVector.getLength()) {
                velocity.direction  = direction;
                path.next = nextNodeIndex + 1;
            } else {
                velocity.direction = velocityVector;
            }
        } else {
            velocity.direction.setX(0);
            velocity.direction.setY(0);
        }
    }

    private Vector2 getVelocityVector(Vector2 direction, float time, float speed) {
        Vector2 velocityVector = new Vector2(direction);
        velocityVector.normalize();
        velocityVector.scale(time*speed);
        return velocityVector;
    }
}
