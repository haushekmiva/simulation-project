package models;

import world.WorldMapConfig;

public class Tree extends Entity {
    public Tree(WorldMapConfig config) {
        super(config);
        this.sign = config.getTreeSign();
    }
}
