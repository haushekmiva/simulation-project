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
                Coordinates cell = new Coordinates(x, y);
                Entity entity = world.getEntity(cell);
                if (entity instanceof Creature creature) {
                    creature.makeMove(cell, world);
                }
            }
        }
    }

}
