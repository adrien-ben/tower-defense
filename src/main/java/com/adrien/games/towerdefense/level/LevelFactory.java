package com.adrien.games.towerdefense.level;

import com.adrien.games.math.Vector2;

/**
 * Level factory.
 */
public class LevelFactory {

    private static final int LEVEL_WIDTH = 10;
    private static final int LEVEL_HEIGHT = 10;
    private static final int CELL_SIZE = 16;

    public static Level createTestLevel() {
        boolean[][] collisionMask = {
                {false, false, true, true, true, true, true, true, true, true},
                {false, false, false, true, true, true, true, true, true, true},
                {false, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, true},
                {true, true, true, true, true, true, true, false, false, true},
                {true, true, true, true, true, true, true, false, false, true},
                {false, false, false, false, false, false, false, false, false, true},
                {false, false, false, false, false, false, false, false, false, true},
                {false, false, false, true, true, true, true, true, true, true},
                {false, false, true, true, true, true, true, true, true, true}
        };
        return new Level(LEVEL_WIDTH, LEVEL_HEIGHT, CELL_SIZE, new Vector2(8, 8), new Vector2(152, 8), collisionMask);
    }

}
