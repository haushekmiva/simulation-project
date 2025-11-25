package models;

import world.WorldMapConfig;

public class Tree extends Entity {

    public Tree(WorldMapConfig config) {
        super(config);
        this.type = EntityType.TREE;
        this.sign = config.getTreeSign();
    }
}
