package com.adrien.games.towerdefense.entity;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.*;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.awt.*;
import java.util.List;

/**
 * Factory responsible of creating entities.
 */
public class EntityFactory {

    public static final int BULLET_GROUP = 0;
    private static final int MINION_GROUP = 1;

    public static Entity create(Component... components) {
        Entity entity = new Entity();
        for(Component component : components) {
            entity.add(component);
        }
        return entity;
    }

    public static Entity createPlayer() {
        return create(new Input(false, new Vector2()));
    }

    public static Entity createTurret(Vector2 position, float size, float range, float rate, int damage) {
        return create(
                new Position(position),
                new Body(size),
                new Tracker(range, null),
                new Shoot(rate, damage, range),
                new Render(Color.BLUE));
    }

    public static Entity createBullet(Vector2 position, float size, float speed, int damage, int health, Entity target) {
        return create(
                new Position(position),
                new Body(size),
                new Velocity(speed, new Vector2()),
                new Collision(BULLET_GROUP),
                new Health(health),
                new Damage(damage),
                new Tracker(0, target),
                new Render(Color.RED));
    }

    public static Entity createMinion(Vector2 position, float size, float speed, List<Vector2> path, int health) {
        return create(
                new Position(position),
                new Body(size),
                new Velocity(speed, new Vector2()),
                new Path(0, path),
                new Collision(MINION_GROUP),
                new Health(health),
                new Render(Color.YELLOW));
    }

    public static Entity createSpawner(Vector2 position, float size, float rate) {
        return create(
                new Position(position),
                new Body(size),
                new Spawner(rate),
                new Render(Color.GRAY));
    }

}
