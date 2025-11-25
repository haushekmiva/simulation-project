package models;

import world.WorldMapConfig;

public abstract class Entity {
    protected String sign;
    protected EntityType type;

    public EntityType getType() {
        return type;
    }

    public Entity(WorldMapConfig config) {

    }

    public String getSign() {
        return sign;
    }
}
