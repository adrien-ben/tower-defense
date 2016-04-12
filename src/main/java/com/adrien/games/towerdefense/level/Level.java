package com.adrien.games.towerdefense.level;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;

/**
 * Game level.
 * The map is a grid whose cell size can be chosen.
 * It has a minion spawn and an objective.
 */
public class Level {

    private final int width;
    private final int height;
    private final double cellSize;
    private final Vector2 minionSpawn;
    private final Vector2 objective;
    private final boolean[][] collisionMask;

    public Level(int width, int height, double cellSize, Vector2 minionSpawn, Vector2 objective) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.minionSpawn = minionSpawn;
        this.objective = objective;
        this.collisionMask = new boolean[width][height];
    }

    /**
     * Gets the path between two positions.
     * The two position have to be accessible.
     * @param start The start position.
     * @param end The end position.
     * @return A path between the start and the end.
     */
    public Path getPath(Vector2 start, Vector2 end) {
        Path path = new Path();
        if(isAccessible(start.getX(), start.getY()) && isAccessible(end.getX(), end.getY())) {
            path.addCheckPoint(new Vector2(start));
            path.addCheckPoint(new Vector2(end));
        }
        return path;
    }

    /**
     * Checks if a position in the map can be accessed.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if the position can be accessed, false otherwise.
     */
    public boolean isAccessible(double x, double y) {
        int gridX = (int)(x/cellSize);
        int gridY = (int)(y/cellSize);
        return gridX >= 0 && gridX <= width && gridY >= 0 && gridY <= height && !collisionMask[gridX][gridY];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getCellSize() {
        return cellSize;
    }

    public Vector2 getMinionSpawn() {
        return minionSpawn;
    }

    public Vector2 getObjective() {
        return objective;
    }
}
