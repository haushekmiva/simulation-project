package utils;

import models.Creature;
import models.Entity;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.ArrayList;
import java.util.List;

public class Actions {

    private boolean isAlive(Creature creature) {
        if (creature.getHealth() > 0) {
            return true;
        } else return false;
    }

    private void addGrass(WorldMap world) {

    }

    public WorldMap initActions() {
        WorldMapConfig config = new WorldMapConfig();
        WorldMap world = new WorldMap(config);
        return world;
    }

    public void turnActions(WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        List<Entity> moved = new ArrayList<>();

        for (int x = 0; x <= height - 1; x++) {
            for (int y = 0; y <= width - 1; y++) {

                Coordinates currentPosition = new Coordinates(x, y);
                Entity entity = world.getEntity(currentPosition);

                if (entity instanceof Creature creature) {
                    if (!isAlive(creature)) {
                        world.deleteEntity(currentPosition);
                        continue;
                    }

                    if (!moved.contains(entity)) {
                        creature.makeMove(world);
                        moved.add(entity);
                    }

                }
            }
        }
    }

}
