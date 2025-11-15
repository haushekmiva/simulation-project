package models;

import world.WorldMapConfig;

public class Grass extends Entity {
    public Grass(WorldMapConfig config) {
        super(config);
        this.sign = config.getGrassSign();
    }
}
