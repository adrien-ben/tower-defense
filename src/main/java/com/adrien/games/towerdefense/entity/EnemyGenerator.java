package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;

/**
 * Generates an enemy every {code rate} ms.
 */
public class EnemyGenerator extends Entity {

    private long rate;
    private long timeSinceLastGeneration;

    public EnemyGenerator(Vector2 position, long rate) {
        super(position);
        this.rate = rate;
        this.timeSinceLastGeneration = 0;
    }

    @Override
    public void update(Timer timer) {
        timeSinceLastGeneration += timer.gelElapsedTime();
        if(timeSinceLastGeneration > rate) {
            world.addEntity(new Enemy(new Vector2(position), 3, 25,
                    world.getLevel().getPath(position, world.getLevel().getObjective())));
            timeSinceLastGeneration -= rate;
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
