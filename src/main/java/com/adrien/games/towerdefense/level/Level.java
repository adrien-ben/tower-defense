package com.adrien.games.towerdefense.level;

import com.adrien.games.math.Vector2;
import com.adrien.games.pathfinding.PathFinder;
import com.adrien.games.pathfinding.graph.Edge;
import com.adrien.games.pathfinding.graph.Graph;
import com.adrien.games.utils.Assert;

import java.util.ArrayList;
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

    public Level(int width, int height, double cellSize, Vector2 minionSpawn, Vector2 objective, boolean[][] collisionMask) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        Assert.isTrue(isInside(minionSpawn), "Minion spawn cannot be placed outside the map.");
        Assert.isTrue(isInside(objective), "Objective cannot be placed outside the map.");
        this.minionSpawn = minionSpawn;
        this.objective = objective;
        this.collisionMask = Assert.isNotNull(collisionMask, "Collision mask cannot be null.");
        this.graph = new Graph<>();
        generateGraph();
    }

    /**
     * Generate the graph containing possible paths of the map.
     */
    private void generateGraph() {
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(!collisionMask[x][y]) {
                    graph.addVertex(new Vector2(x, y));
                }
            }
        }
        for(int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                if(!collisionMask[x][y]) {
                    bindNeighbors(x, y, false);
                }
            }
        }
    }

    /**
     * Add edge in the graph between the node ({@code x}, {@code y}) and its neighbors.
     * You can choose the edge to be bidirectional or not.
     * @param x The x position of the node.
     * @param y The y position of the node.
     * @param bidirectional Is the edge uni or bidirectional.
     */
    private void bindNeighbors(int x, int y, boolean bidirectional) {
        int xMin = x > 0 ? x - 1 : 0;
        int xMax = x < width - 1 ? x + 1 : width - 1;
        int yMin = y > 0 ? y - 1 : 0;
        int yMax = y < height - 1 ? y + 1 : height - 1;
        for(int xx = xMin; xx <= xMax; xx++) {
            for(int yy = yMin; yy <= yMax; yy++) {
                if((x != xx || y != yy) && !collisionMask[xx][yy]) {
                    Vector2 source = new Vector2(x, y);
                    Vector2 target = new Vector2(xx, yy);
                    double distance = x == xx || y == yy ? 1 : 1.2;
                    graph.addEdge(source, target, new Edge(distance));
                    if(bidirectional) {
                        graph.addEdge(target, source, new Edge(distance));
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
    public List<Vector2> getPath(Vector2 start, Vector2 end) {
        List<Vector2> path = new ArrayList<>();
        if(isAccessible(start) && isAccessible(end)) {
            List<Vector2> foundPath = PathFinder.findPath(
                    graph,
                    new Vector2((int) (start.getX() / cellSize), (int) (start.getY() / cellSize)),
                    new Vector2((int) (end.getX() / cellSize), (int) (end.getY() / cellSize)),
                    (v1, v2) -> {
                        double xDist = v2.getX() - v1.getX();
                        double yDist = v2.getY() - v1.getY();
                        return xDist * xDist + yDist * yDist;
                    }
            );
            path.add(new Vector2(start));
            for(int i = 1; i < foundPath.size() - 2; i++) {
                Vector2 nextPosition = foundPath.get(i);
                path.add(new Vector2(nextPosition.getX()*cellSize + cellSize/2,
                        nextPosition.getY()*cellSize + cellSize/2));
            }
            path.add(end);
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
        return x >= 0 && x < width*cellSize && y >= 0 && y < height*cellSize;
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
