package com.adrien.games.towerdefense.level;

import com.adrien.games.math.Vector2;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.utils.Assert;

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
        Assert.isTrue(isInside(minionSpawn), "Minion spawn cannot be placed outside the map.");
        this.minionSpawn = minionSpawn;
        Assert.isTrue(isInside(objective), "Objective cannot be placed outside the map.");
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
        if(isAccessible(start) && isAccessible(end)) {
            path.addCheckPoint(new Vector2(start));
            path.addCheckPoint(new Vector2(end));
        }
        return path;
    }

    /**
     * Checks if a position in the map can be accessed.
     * @param position THe position to check.
     * @return True if the position can be accessed, false otherwise.
     */
    public boolean isAccessible(Vector2 position) {
        int gridX = (int)(position.getX()/cellSize);
        int gridY = (int)(position.getY()/cellSize);
        return isInside(position) && !collisionMask[gridX][gridY];
    }

    /**
     * Checks if a position is contained inside map boundaries.
     * @param position The position to check.
     * @return True if the position is inside the map, false otherwise.
     */
    private boolean isInside(Vector2 position) {
        double x = position.getX();
        double y = position.getY();
        return x > 0 && x < width*cellSize && y > 0 && y < height*cellSize;
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
