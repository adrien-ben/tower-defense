package com.adrien.games.towerdefense.world;

import com.adrien.games.application.Timer;
import com.adrien.games.towerdefense.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Game world manages entities.
 */
public class World {

    private final List<Entity> entities;
    private final List<Entity> entitiesBuffer;
    private final Map<String, List<Entity>> entitiesByType;

    public World() {
        entities = new ArrayList<>();
        entitiesBuffer = new ArrayList<>();
        entitiesByType = new HashMap<>();
    }

    /**
     * Adds buffered entities then updates all entities.
     * Finally it removes all dead entities.
     * @param timer The application timer
     */
    public void update(Timer timer) {
        entitiesBuffer.stream().filter(Entity::isAlive).forEach(this::addBufferedEntity);
        entitiesBuffer.clear();
        entities.stream().forEach(entity -> entity.update(timer));
        removeDeadEntities();
    }

    /**
     * Removes dead entities.
     */
    private void removeDeadEntities() {
        entities.removeIf(entity -> !entity.isAlive());
        entitiesByType.values().forEach(entityList -> entityList.removeIf(entity -> !entity.isAlive()));
    }

    /**
     * Adds a new entity the the world.
     * The added entity is first added to a buffer to avoid concurrency.
     * The entity will actually be added at the beginning of the next {@code update} call.
     * @param entity The entity to add.
     */
    public void addEntity(Entity entity) {
        entitiesBuffer.add(entity);
    }

    /**
     * Adds an entity from the buffer.
     * @param entity The entity to add.
     */
    private void addBufferedEntity(Entity entity) {
        String clazz = entity.getClass().getSimpleName();
        List<Entity> entitiesOfSameClass = entitiesByType.get(clazz);
        if(entitiesOfSameClass == null) {
            entitiesOfSameClass = new ArrayList<>();
            entitiesByType.put(clazz, entitiesOfSameClass);
        }
        entities.add(entity);
        entitiesOfSameClass.add(entity);
        entity.setWorld(this);
    }

    /**
     * Gets all entities for a given class.
     * Return an empty list if no entity could be found for
     * the given class.
     * @param clazz The class of the entity.
     * @return A list of entities.
     */
    public List<Entity> getEntities(Class clazz) {
        List<Entity> entityForClass = entitiesByType.get(clazz.getSimpleName());
        return entityForClass == null ? new ArrayList<>() : new ArrayList<>(entityForClass);
    }
}
