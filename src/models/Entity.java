package models;

import world.WorldMapConfig;

public abstract class Entity {
    protected String sign;

    public Entity(WorldMapConfig config) {

    }

    public String getSign() {
        return sign;
    }
}
