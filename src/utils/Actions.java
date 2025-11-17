package utils;

import models.Creature;
import models.Entity;
import world.WorldMap;
import world.WorldMapConfig;

public class Actions {

    public WorldMap initActions() {
        WorldMapConfig config = new WorldMapConfig();
        WorldMap world = new WorldMap(config);
        return world;
    }

    public void turnActions(WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        for (int x = 0; x <= height; x++) {
            for (int y = 0; y <= width; y++) {
                Entity entity = world.getEntity(new Coordinates(x, y));
                if (entity instanceof Creature creature) {
                    creature.makeMove(new Coordinates(x, y), world);
                }
            }
        }
    }

}
