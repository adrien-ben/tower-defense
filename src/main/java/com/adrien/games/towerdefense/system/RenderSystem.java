package com.adrien.games.towerdefense.system;

import com.adrien.games.towerdefense.component.Body;
import com.adrien.games.towerdefense.component.Position;
import com.adrien.games.towerdefense.component.Render;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

import java.awt.*;

/**
 * Render entities.
 */
public class RenderSystem extends EntitySystem {

    private final ComponentMapper<Position> positionMapper = ComponentMapper.getFor(Position.class);
    private final ComponentMapper<Body> bodyMapper = ComponentMapper.getFor(Body.class);
    private final ComponentMapper<Render> renderMapper = ComponentMapper.getFor(Render.class);

    private ImmutableArray<Entity> entities;
    private Graphics2D graphics2D;

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(Position.class, Render.class).get());
        setProcessing(false);
    }

    @Override
    public void removedFromEngine(Engine engine) {
    }

    @Override
    public void update(float deltaTime) {
        for(Entity entity : entities) {
            if(graphics2D != null) {
                Position position = positionMapper.get(entity);
                Body body = bodyMapper.get(entity);
                Render render = renderMapper.get(entity);
                int size = (int)body.getSize();
                graphics2D.setColor(render.getColor());
                if(render.isFilled()) {
                    graphics2D.fillOval((int)position.getPosition().getX() - size/2,
                            (int)position.getPosition().getY() - size/2, size, size);
                } else {
                    graphics2D.drawOval((int)position.getPosition().getX() - size/2,
                            (int)position.getPosition().getY() - size/2, size, size);
                }
            }
        }
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }
}
