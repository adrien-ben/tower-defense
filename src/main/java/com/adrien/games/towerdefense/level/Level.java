package com.adrien.games.towerdefense.level;

import com.adrien.games.math.Vector2;
import com.adrien.games.pathfinding.PathFinder;
import com.adrien.games.pathfinding.graph.Edge;
import com.adrien.games.pathfinding.graph.Graph;
import com.adrien.games.towerdefense.animation.Path;
import com.adrien.games.utils.Assert;

import java.util.List;

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
    private final Graph<Vector2> graph;

    public Level(int width, int height, double cellSize, Vector2 minionSpawn, Vector2 objective) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        Assert.isTrue(isInside(minionSpawn), "Minion spawn cannot be placed outside the map.");
        Assert.isTrue(isInside(objective), "Objective cannot be placed outside the map.");
        this.minionSpawn = minionSpawn;
        this.objective = objective;
        this.collisionMask = new boolean[width][height];
        this.graph = new Graph<>();
        generateGraph();
    }

    private void generateGraph() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(!collisionMask[i][j]) {
                    graph.addVertex(new Vector2(i, j));
                }
            }
        }
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                if(!collisionMask[i][j]) {
                    int xMin = i > 0 ? i - 1 : 0;
                    int xMax = i < width - 1 ? i + 1 : width - 1;
                    int yMin = j > 0 ? j - 1 : 0;
                    int yMax = j < height - 1 ? j + 1 : height - 1;
                    for(int ii = xMin; ii <= xMax; ii++) {
                        for(int jj = yMin; jj <= yMax; jj++) {
                            if(i != ii || j != jj) {
                                Vector2 source = new Vector2(i, j);
                                Vector2 target = new Vector2(ii, jj);
                                double distance = i == ii || j == jj ? 1 : 0.8;
                                graph.addEdge(source, target, new Edge(distance));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets the path between two positions using path finding.
     * The two position have to be accessible.
     * @param start The start position.
     * @param end The end position.
     * @return A path between the start and the end.
     */
    public Path getPath(Vector2 start, Vector2 end) {
        Path path = new Path();
        if(isAccessible(start) && isAccessible(end)) {
            List<Vector2> foundPath = PathFinder.findPath(
                    graph,
                    new Vector2((int)(start.getX()/cellSize), (int)(start.getY()/cellSize)),
                    new Vector2((int)(end.getX()/cellSize), (int)(end.getY()/cellSize)),
                    (v1, v2) -> {
                        double xDist = v2.getX() - v1.getX();
                        double yDist = v2.getY() - v1.getY();
                        return Math.sqrt(xDist * xDist + yDist * yDist);
                    }
            );
            path.addCheckPoint(new Vector2(start));
            for(int i = 1; i < foundPath.size() - 2; i++) {
                Vector2 nextPosition = foundPath.get(i);
                path.addCheckPoint(new Vector2(nextPosition.getX()*cellSize + cellSize/2,
                        nextPosition.getY()*cellSize + cellSize/2));
            }
            path.addCheckPoint(end);
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
