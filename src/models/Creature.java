package models;

import world.WorldMap;

import java.awt.*;

public abstract class Creature extends Entity {
    protected int speed;
    protected int health;

    public abstract void makeMove(Point currentPosition, WorldMap map);

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}
