package com.adrien.games.towerdefense.system;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.*;
import com.adrien.games.towerdefense.entity.EntityFactory;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Checks and resolves collision between entities from different collision groups
 */
public class CollisionSystem extends EntitySystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Body> bodyMapper = ComponentMapper.getFor(Body.class);
    private final ComponentMapper<Collision> collisionMapper = ComponentMapper.getFor(Collision.class);
    private final ComponentMapper<Damage> damageMapper = ComponentMapper.getFor(Damage.class);
    private final ComponentMapper<Health> healthMapper = ComponentMapper.getFor(Health.class);

    private ImmutableArray<Entity> entities;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Position.class, Body.class, Collision.class).get());
    }

    @Override
    public void update(float deltaTime) {

        for(int i = 0; i < entities.size(); i++) {
            for(int j = i + 1; j < entities.size() && i != j; j++) {

                Entity entity1 = entities.get(i);
                Entity entity2 = entities.get(j);

                Collision collision1 = collisionMapper.get(entity1);
                Collision collision2 = collisionMapper.get(entity2);

                if(entity1 != entity2 && collision1.getGroup() != collision2.getGroup()) {

                    Position position1 = positionMapper.get(entity1);
                    Body body1 = bodyMapper.get(entity1);
                    Position position2 = positionMapper.get(entity2);
                    Body body2 = bodyMapper.get(entity2);

                    float distance = new Vector2(position1.getPosition().getX() - position2.getPosition().getX(),
                            position1.getPosition().getY() - position2.getPosition().getY()).getLength();

                    if(distance <= body1.getSize()/2 + body2.getSize()/2) {
                        if(collision1.getGroup() == EntityFactory.BULLET_GROUP && collision2.getGroup() == EntityFactory.MINION_GROUP) {
                            hit(entity1, entity2);
                            getEngine().removeEntity(entity1);
                        } else if(collision1.getGroup() == EntityFactory.MINION_GROUP && collision2.getGroup() == EntityFactory.BULLET_GROUP) {
                            hit(entity2, entity1);
                            getEngine().removeEntity(entity2);
                        }

                    }

                }
            }
        }
    }

    private void hit(Entity source, Entity target) {
        Damage damage = damageMapper.get(source);
        Health health = healthMapper.get(target);
        if(health != null && damage != null) {
            health.setAmount(health.getAmount() - damage.getAmount());
        }
    }
}
