package com.adrien.games.towerdefense.level;

import com.adrien.games.towerdefense.animation.Path;

import java.util.List;

/**
 * Game level.
 */
public class Map {

    private final double width;
    private final double height;
    private final List<Path> paths;

    public Map(int width, int height, List<Path> paths) {
        this.width = width;
        this.height = height;
        this.paths = paths;
    }

    /**
     * Checks if a position contained inside level boundaries.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if contained, false otherwise.
     */
    public boolean isInMap(double x, double y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
