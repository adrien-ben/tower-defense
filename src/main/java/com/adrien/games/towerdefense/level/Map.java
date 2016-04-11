package com.adrien.games.towerdefense.level;

/**
 * Game level.
 * The map is a grid whose cell size can be chosen.
 */
public class Map {

    private final int width;
    private final int height;
    private final double cellSize;
    private final boolean[][] collisionMask;

    public Map(int width, int height, double cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.collisionMask = new boolean[width][height];
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

}
