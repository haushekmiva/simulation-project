package utils;

import world.WorldMap;
import world.WorldMapConfig;

public class Actions {

    public WorldMap initActions() {
        WorldMapConfig config = new WorldMapConfig();
        WorldMap world = new WorldMap(config);
        return world;
    }

    public void turnActions(WorldMap map) {

    }

}
