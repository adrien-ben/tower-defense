package com.adrien.games.towerdefense;

import com.adrien.games.application.GameApplication;
import com.adrien.games.application.GameSettings;
import com.adrien.games.application.Input;
import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.entity.*;
import com.adrien.games.towerdefense.level.Level;
import com.adrien.games.towerdefense.world.World;

import java.awt.*;

/**
 * Main class of the game.
 */
public class TowerDefense extends GameApplication {

    private static final String NAME = "Tower defense";
    private static final String VERSION = "v0.1";
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int PIXELS_PER_UNIT = 16;
    private static final int LEVEL_WIDTH = 30;
    private static final int LEVEL_HEIGHT = 30;
    private static final long ENEMY_GENERATION_RATE = 1000;
    private static final long TURRET_RATE = 1000;
    private static final double TURRET_RANGE = 3;
    private static final double CELL_SIZE = 1;
    private static final double TURRET_SIZE = 1;
    private static final double ENEMY_SIZE = 0.5;
    private static final double BULLET_SIZE = 0.2;

    private Level level = new Level(LEVEL_WIDTH, LEVEL_HEIGHT, CELL_SIZE, new Vector2(0.5, 0.5), new Vector2(29.5, 29.5));
    private World world = new World(level);

    @Override
    protected void init(GameSettings gameSettings) {
        gameSettings.setName(NAME + " " + VERSION);
        gameSettings.setWidth(SCREEN_WIDTH);
        gameSettings.setHeight(SCREEN_HEIGHT);
        world.addEntity(new EnemyGenerator(level.getMinionSpawn(), ENEMY_GENERATION_RATE));
    }

    @Override
    protected void handleInput(Input input) {
        if(input.wasBtnPressed(Input.BTN_LEFT)) {
            Vector2 position = new Vector2((double)input.getMouseX()/PIXELS_PER_UNIT,
                    (double)input.getMouseY()/PIXELS_PER_UNIT);
            if(level.isAccessible(position)) {
                world.addEntity(new Turret(position, TURRET_RANGE, TURRET_RATE));
            }
        }
    }

    @Override
    protected void update(Timer timer) {
        world.update(timer);
    }

    @Override
    protected void render(Graphics2D graphics2D) {
        renderLevel(graphics2D);
        graphics2D.setColor(Color.BLUE);
        int enemySize = (int)(ENEMY_SIZE*PIXELS_PER_UNIT);
        world.getEntities(Enemy.class).stream().forEach(enemy -> renderEntity(graphics2D, enemy, enemySize));
        graphics2D.setColor(Color.GREEN);
        int turretSize = (int)(TURRET_SIZE*PIXELS_PER_UNIT);
        world.getEntities(Turret.class).stream().forEach(turret -> renderEntity(graphics2D, turret, turretSize));
        graphics2D.setColor(Color.RED);
        int bulletSize = (int)(BULLET_SIZE*PIXELS_PER_UNIT);
        world.getEntities(Bullet.class).stream().forEach(bullet -> renderEntity(graphics2D, bullet, bulletSize));
    }

    private void renderLevel(Graphics2D graphics2D) {
        int cellSize = (int)(level.getCellSize()*PIXELS_PER_UNIT);
        graphics2D.setColor(Color.ORANGE);
        graphics2D.fillOval((int)(level.getMinionSpawn().getX()*PIXELS_PER_UNIT) - cellSize/2, (int)(level.getMinionSpawn().getY()*PIXELS_PER_UNIT) - cellSize/2, cellSize, cellSize);
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval((int)(level.getObjective().getX()*PIXELS_PER_UNIT) - cellSize/2, (int)(level.getObjective().getY()*PIXELS_PER_UNIT) - cellSize/2, cellSize, cellSize);
        graphics2D.setColor(Color.LIGHT_GRAY);
        for(int column = 0; column < level.getWidth(); column++) {
            for(int row = 0; row < level.getHeight(); row++) {
                graphics2D.drawRect(column*cellSize, row*cellSize, cellSize, cellSize);
            }
        }
    }

    private void renderEntity(Graphics2D graphics2D, Entity entity, int size) {
        Vector2 position = entity.getPosition();
        graphics2D.drawOval((int)(position.getX()*PIXELS_PER_UNIT) - size/2, (int)(position.getY()*PIXELS_PER_UNIT) - size/2, size, size);
    }

    @Override
    protected void dispose() {

    }

    public static void main(String[] args) {
        new TowerDefense().start();
    }
}
