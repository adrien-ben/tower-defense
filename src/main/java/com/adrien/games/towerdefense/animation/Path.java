package com.adrien.games.towerdefense.animation;

import com.adrien.games.math.Vector2;
import com.adrien.games.utils.Assert;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * A path is an list of positions to go through.
 */
public class Path {

    private Deque<Vector2> checkPoints;
    private Vector2 position;

    public Path() {
        checkPoints = new ArrayDeque<>();
        position = null;
    }

    /**
     * Adds a new checkpoint at the end of the path.
     * @param checkPoint The checkpoint to add. It cannot be null.
     */
    public void addCheckPoint(Vector2 checkPoint) {
        Assert.isNotNull(checkPoint, "Check point should not be null.");
        checkPoints.add(checkPoint);
        if(position == null) {
            position = checkPoint;
        }
    }

    /**
     * Update the current position in the path.
     * @param speed The speed in units/second.
     * @param elapsed The time to pass in second.
     */
    public void update(double speed, double elapsed) {
        if(!isEndReached()) {
            Vector2 target = checkPoints.getFirst();
            Vector2 direction = new Vector2(target.getX() - position.getX(), target.getY() - position.getY());
            double distanceToTarget = direction.getLength();
            direction.normalize();
            direction.scale(speed*elapsed);
            double distanceToMake = direction.getLength();
            if(distanceToMake >= distanceToTarget) {
                reachedCheckPoint();
            } else {
                position.add(direction);
            }
        }
    }

    /**
     * Method called when the position reached a checkpoint.
     * It removes the next position in the queue.
     */
    private void reachedCheckPoint() {
        position = checkPoints.pollFirst();
    }

    public Deque<Vector2> getCheckPoints() {
        return checkPoints;
    }

    public Vector2 getPosition() {
        return position;
    }

    public boolean isEndReached() {
        return checkPoints.isEmpty();
    }
}
