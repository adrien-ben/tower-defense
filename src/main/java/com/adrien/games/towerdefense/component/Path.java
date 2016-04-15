package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

import java.util.List;

public class Path implements Component {

    private int next;
    private List<Vector2> positions;

    public Path(int next, List<Vector2> positions) {
        this.next = next;
        this.positions = positions;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public List<Vector2> getPositions() {
        return positions;
    }

    public void setPositions(List<Vector2> positions) {
        this.positions = positions;
    }
}
