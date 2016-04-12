package com.adrien.games.towerdefense.entity;

import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;

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
            Path path = new Path();
            path.addCheckPoint(new Vector2(world.getLevel().getMinionSpawn()));
            path.addCheckPoint(new Vector2(world.getLevel().getObjective()));
            world.addEntity(new Enemy(new Vector2(world.getLevel().getMinionSpawn()), 3, 25, path));
            timeSinceLastGeneration -= rate;
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
