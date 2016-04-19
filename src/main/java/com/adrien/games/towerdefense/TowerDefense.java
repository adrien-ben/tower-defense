package com.adrien.games.towerdefense;

import com.adrien.games.application.GameApplication;
import com.adrien.games.application.GameSettings;
import com.adrien.games.application.Input;
import com.adrien.games.application.Timer;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.entity.EntityFactory;
import com.adrien.games.towerdefense.level.Level;
import com.adrien.games.towerdefense.level.LevelFactory;
import com.adrien.games.towerdefense.player.Player;
import com.adrien.games.towerdefense.system.*;
import com.badlogic.ashley.core.Engine;

import java.awt.*;

/**
 * Main class of the game.
 */
public class TowerDefense extends GameApplication {

    private static final String NAME = "Tower defense";
    private static final String VERSION = "v0.1";
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final boolean DEBUG = true;
    private static final int PIXELS_PER_UNIT = 1;
    private static final float SPAWNER_RATE = 2;
    private static final float SPAWNER_SIZE = 12;

    private float lastFrameTime = 0;

    private Level level = LevelFactory.createTestLevel();
    private Player player = new Player();
    private Engine engine = new Engine();
    private PlayerSystem playerSystem = new PlayerSystem(player, level);
    private RenderSystem renderSystem = new RenderSystem();

    @Override
    protected void init(GameSettings gameSettings) {
        gameSettings.setName(NAME + " " + VERSION);
        gameSettings.setWidth(SCREEN_WIDTH);
        gameSettings.setHeight(SCREEN_HEIGHT);

        engine.addEntity(EntityFactory.createSpawner(
                new Vector2(level.getMinionSpawn()),
                SPAWNER_SIZE,
                SPAWNER_RATE));
        engine.addSystem(playerSystem);
        engine.addSystem(new MinionSystem());
        engine.addSystem(new BulletSystem());
        engine.addSystem(new MovementSystem());
        engine.addSystem(new CollisionSystem());
        engine.addSystem(new TargetingSystem());
        engine.addSystem(new TurretSystem());
        engine.addSystem(new SpawnSystem(level));
        engine.addSystem(new HealthSystem());
        engine.addSystem(renderSystem);
    }

    @Override
    protected void handleInput(Input input) {
        playerSystem.setInput(input);
    }

    @Override
    protected void update(Timer timer) {
        this.lastFrameTime = timer.gelElapsedTime();
        engine.update((float) timer.gelElapsedTime() / Timer.MS_PER_SECOND);
    }

    @Override
    protected void render(Graphics2D graphics2D) {
        renderLevel(graphics2D);
        renderSystem.setGraphics2D(graphics2D);
        renderSystem.update(0);
        renderUI(graphics2D);
        if(DEBUG) {
            renderDebug(graphics2D);
        }
    }

    private void renderUI(Graphics2D graphics2D) {
        graphics2D.setColor(Color.ORANGE);
        graphics2D.drawString("" + player.getMoney(), 10, 10);
    }

    private void renderLevel(Graphics2D graphics2D) {
        int cellSize = (int)(level.getCellSize()*PIXELS_PER_UNIT);
        graphics2D.setColor(Color.GRAY);
        graphics2D.fillOval((int)(level.getObjective().getX()*PIXELS_PER_UNIT) - cellSize/2, (int)(level.getObjective().getY()*PIXELS_PER_UNIT) - cellSize/2, cellSize, cellSize);
        graphics2D.setColor(Color.LIGHT_GRAY);
        for(int x = 0; x < level.getWidth(); x++) {
            for(int y = 0; y < level.getHeight(); y++) {
                if(level.isAccessible(new Vector2(x*cellSize, y*cellSize))) {
                    graphics2D.drawRect(x*cellSize, y*cellSize, cellSize, cellSize);
                } else {
                    graphics2D.fillRect(x*cellSize, y*cellSize, cellSize, cellSize);
                }
            }
        }
    }

    private void renderDebug(Graphics2D graphics2D) {
        graphics2D.setColor(Color.GREEN);
        graphics2D.drawString("ft: " + this.lastFrameTime + "ms", 10, SCREEN_HEIGHT - 10);
        graphics2D.drawString("entities: " + this.engine.getEntities().size(), 10, SCREEN_HEIGHT - 30);

    }

    @Override
    protected void dispose() {

    }

    public static void main(String[] args) {
        new TowerDefense().start();
    }
}
