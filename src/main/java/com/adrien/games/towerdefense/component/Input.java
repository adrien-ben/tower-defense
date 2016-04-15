package com.adrien.games.towerdefense.component;

import com.adrien.games.math.Vector2;
import com.badlogic.ashley.core.Component;

public class Input implements Component {

    private boolean clicked;
    private Vector2 position;

    public Input(boolean clicked, Vector2 position) {
        this.clicked = clicked;
        this.position = position;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
