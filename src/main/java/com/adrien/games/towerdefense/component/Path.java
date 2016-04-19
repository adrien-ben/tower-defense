package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

import java.util.List;

public class Path implements Component {

    public int next;
    public List<Vector2> positions;

    public Path(int next, List<Vector2> positions) {
        this.next = next;
        this.positions = positions;
    }

}
