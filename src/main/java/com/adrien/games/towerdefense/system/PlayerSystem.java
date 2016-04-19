package com.adrien.games.towerdefense.system;

import com.adrien.games.application.Input;
import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.component.Loot;
import com.adrien.games.towerdefense.entity.EntityFactory;
import com.adrien.games.towerdefense.level.Level;
import com.adrien.games.towerdefense.player.Player;
import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Places tower on click if player have enough money. And collect loots.
 */
public class PlayerSystem extends EntitySystem {

    private static final float TURRET_RATE = 0.8f;
    private static final float TURRET_RANGE = 60;
    private static final float TURRET_SIZE = 12;
    private static final int TURRET_DAMAGE = 1;

    private final ComponentMapper<Loot> lootMapper = ComponentMapper.getFor(Loot.class);

    private ImmutableArray<Entity> loots;
    private Player player;
    private Level level;
    private Input input;

    public PlayerSystem(Player player, Level level) {
        this.player = player;
        this.level = level;
    }

    @Override
    public void addedToEngine(Engine engine) {
        loots = engine.getEntitiesFor(Family.all(Loot.class).get());
    }

    @Override
    public void update(float deltaTime) {
        collectLoot();
        placeTower();
    }

    private void collectLoot() {
        for(Entity loot : loots) {
            player.addMoney(lootMapper.get(loot).getMoney());
            getEngine().removeEntity(loot);
        }
    }

    private void placeTower() {
        if(input != null && input.wasBtnPressed(Input.BTN_LEFT)) {
            Vector2 mousePosition = new Vector2(input.getMouseX(), input.getMouseY());
            if (!level.isAccessible(mousePosition)) {
                getEngine().addEntity(EntityFactory.createTurret(
                        mousePosition,
                        TURRET_SIZE,
                        TURRET_RANGE,
                        TURRET_RATE,
                        TURRET_DAMAGE));
            }
        }
    }

    public void setInput(Input input) {
        this.input = input;
    }
}
