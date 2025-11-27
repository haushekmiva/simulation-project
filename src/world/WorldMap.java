package world;

import models.*;
import utils.BFS;
import utils.Coordinates;

import java.util.*;


public class WorldMap {
    private final WorldMapConfig config;
    private final BFS bfs = new BFS();
    private final EntityFactory entityFactory = new EntityFactory();

    private final int width;
    private final int height;
    private static final double RESPAWN_THRESHOLD = 0.3;

    private Map<Coordinates, Entity> entityMap = new HashMap<>();
    private Map<Entity, Coordinates> coordinatesMap = new HashMap<>();


    Map<EntityType, Integer> limits = new EnumMap<>(EntityType.class);
    Map<EntityType, Integer> counts = new EnumMap<>(EntityType.class);

    public WorldMap(WorldMapConfig config) {
        this.config = config;
        this.width = config.getWidth();
        this.height = config.getHeight();

        for (EntityType type : EntityType.values()) {
            int limit = switch (type) {
                case HERBIVORE -> config.getHerbivoreLimit();
                case PREDATOR -> config.getPredatorLimit();
                case ROCK -> config.getRockLimit();
                case TREE -> config.getTreeLimit();
                case GRASS -> config.getGrassLimit();
            };
            limits.put(type, limit);
        }

        for (EntityType type : EntityType.values()) {
            counts.put(type, 0);
        }

        for (EntityType type : EntityType.values()) {
            spawnEntity(type ,limits.get(type));
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void changeCountOfEntity(Entity entity, int value) {
        int count = counts.get(entity.getType());
        counts.put(entity.getType(), count + value);
    }

    public boolean isCellEmpty(Coordinates cell) {
        return !entityMap.containsKey(cell);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entityMap.get(coordinates);
    }

    public void setEntity(Coordinates to, Entity entity) {
        entityMap.put(to, entity);
        coordinatesMap.put(entity, to);
        changeCountOfEntity(entity, 1);
    }

    public void deleteEntity(Coordinates from) {
        Entity entity = entityMap.get(from);
        entityMap.remove(from);
        coordinatesMap.remove(entity);
        changeCountOfEntity(entity, -1);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        deleteEntity(from);
        setEntity(to, entity);
    }


    public Coordinates getEntityPosition(Entity entity) {
        return coordinatesMap.get(entity);
    }


    public void balanceWorld() {
        List<EntityType> entitiesToBalance = new ArrayList<>(List.of(EntityType.HERBIVORE, EntityType.GRASS));

        for (EntityType type : entitiesToBalance) {
            int limit = limits.get(type);

            while (counts.get(type) < limit * RESPAWN_THRESHOLD) {
                spawnEntity(type, 1);
            }

        }

    }


    /**
     * Spawns an entity at a random location on the map.
     * Determines a random position, calls the factory,
     * and increments the counter for this entity type.
     *
     * @param type the type of creature to spawn
     * @param limit the number of creatures to add
     */
    private void spawnEntity(EntityType type, int limit) {
        int count = 0;
        while (count < limit) {
            int x = (int) (Math.random() * height);
            int y = (int) (Math.random() * width);
            Coordinates coordinates = new Coordinates(x, y);
            if (entityMap.get(coordinates) == null) {
                Entity entity = entityFactory.createEntity(type, config, bfs);
                setEntity(coordinates, entity);
                count++;
            }
        }
    }


}

