package utils;

import models.Creature;
import models.Entity;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Actions {

    private boolean isAlive(Creature creature) {
        return creature.getHealth() > 0;
    }

    public WorldMap initActions(WorldMapConfig config) {
        return new WorldMap(config);
    }

    /**
     * Performs a single turn.
     * Iterates over all creatures, executes a turn for each,
     * and monitors their state. After completing the turn,
     * calls the map balancing method.
     *
     * @param world the world object used for interacting with the map
     */
    public void turnActions(WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        Set<Entity> moved = new HashSet<>();

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

        world.balanceWorld();
    }

}
