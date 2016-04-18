package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Target;
import com.adrien.games.towerdefense.component.Tracker;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Forget out of range targets.
 * Acquire targets in range if no current target
 */
public class TargetingSystem extends EntitySystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Tracker> trackerMapper = ComponentMapper.getFor(Tracker.class);

    private ImmutableArray<Entity> trackers;
    private ImmutableArray<Entity> targets;

    @Override
    public void addedToEngine(Engine engine) {
        trackers = engine.getEntitiesFor(Family.all(Position.class, Tracker.class).get());
        targets = engine.getEntitiesFor(Family.all(Position.class, Target.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity tracker : trackers) {
            Position trackerPosition = positionMapper.get(tracker);
            Tracker trackerComponent = trackerMapper.get(tracker);

            if(trackerComponent.getEntity() != null && trackerComponent.getRange() > 0) {
                Position currentTargetPosition = positionMapper.get(trackerComponent.getEntity());
                if(currentTargetPosition != null) {
                    float distance = (float)new Vector2(
                            currentTargetPosition.getPosition().getX() - trackerPosition.getPosition().getX(),
                            currentTargetPosition.getPosition().getY() - trackerPosition.getPosition().getY()).getLength();
                    if(distance > trackerComponent.getRange()) {
                        trackerComponent.setEntity(null);
                    }
                }
            }

            if(trackerComponent.getEntity() == null) {
                for(Entity target : targets) {
                    Position targetPosition = positionMapper.get(target);
                    float distance = (float)new Vector2(
                            targetPosition.getPosition().getX() - trackerPosition.getPosition().getX(),
                            targetPosition.getPosition().getY() - trackerPosition.getPosition().getY()).getLength();
                    if(distance <= trackerComponent.getRange()) {
                        trackerComponent.setEntity(target);
                    }

                }
            }
        }
    }
}
