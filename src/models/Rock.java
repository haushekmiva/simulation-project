package models;

import world.WorldMapConfig;

public class Rock extends Entity {
    public Rock(WorldMapConfig config) {
        super(config);
        this.sign = config.getRockSign();
    }
}
