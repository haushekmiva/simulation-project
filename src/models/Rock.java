package models;

import world.WorldMapConfig;

public class Rock extends Entity {

    public Rock(WorldMapConfig config) {
        super(config);
        this.type = EntityType.ROCK;
        this.sign = config.getRockSign();
    }
}
