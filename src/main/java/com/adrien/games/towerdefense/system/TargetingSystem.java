package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Target;
import com.adrien.games.towerdefense.component.Tracker;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Forget out of range targets.
 * Acquire targets in range if no current target
 */
public class TargetingSystem extends EntitySystem implements EntityListener {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Tracker> trackerMapper = ComponentMapper.getFor(Tracker.class);

    private final List<Entity> removedTargets = new ArrayList<>();
    private ImmutableArray<Entity> trackers;
    private ImmutableArray<Entity> targets;

    @Override
    public void addedToEngine(Engine engine) {
        getEngine().addEntityListener(Family.all(Target.class).get(), this);
        trackers = engine.getEntitiesFor(Family.all(Position.class, Tracker.class).get());
        targets = engine.getEntitiesFor(Family.all(Position.class, Target.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(Entity tracker : trackers) {
            Position trackerPosition = positionMapper.get(tracker);
            Tracker trackerComponent = trackerMapper.get(tracker);

            if(removedTargets.contains(trackerComponent.entity)) {
                trackerComponent.entity = null;
            }

            if(trackerComponent.entity != null && trackerComponent.range > 0) {
                Position currentTargetPosition = positionMapper.get(trackerComponent.entity);
                if(currentTargetPosition != null) {
                    float distance = new Vector2(
                            currentTargetPosition.position.getX() - trackerPosition.position.getX(),
                            currentTargetPosition.position.getY() - trackerPosition.position.getY()).getLength();
                    if(distance > trackerComponent.range) {
                        trackerComponent.entity = null;
                    }
                }
            }

            if(trackerComponent.entity == null) {
                for(Entity target : targets) {
                    Position targetPosition = positionMapper.get(target);
                    float distance = new Vector2(
                            targetPosition.position.getX() - trackerPosition.position.getX(),
                            targetPosition.position.getY() - trackerPosition.position.getY()).getLength();
                    if(distance <= trackerComponent.range) {
                        trackerComponent.entity = target;
                    }

                }
            }
        }
        removedTargets.clear();
    }

    @Override
    public void entityAdded(Entity entity) {
    }

    @Override
    public void entityRemoved(Entity entity) {
        removedTargets.add(entity);
    }
}
