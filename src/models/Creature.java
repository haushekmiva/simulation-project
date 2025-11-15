package models;

import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;

public abstract class Creature extends Entity {
    protected int speed;
    protected int health;

    public Creature(WorldMapConfig config) {
        super(config);
    }

    public abstract void makeMove(Coordinates currentPosition, WorldMap map);

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}
