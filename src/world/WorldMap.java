package world;

import models.*;
import models.herbivore.Herbivore;
import models.predator.Predator;
import utils.BFS;
import utils.Coordinates;

import java.util.*;


public class WorldMap {
    private WorldMapConfig config = new WorldMapConfig();
    private final BFS bfs = new BFS();

    private int width;
    private int height;
    private static final double BOARD_OF_COUNT = 0.3;

    private Map<Coordinates, Entity> entityMap = new HashMap<>();
    private Map<Entity, Coordinates> coordinatesMap = new HashMap<>();


    Map<EntityType, Integer> limits = new EnumMap<>(EntityType.class);
    Map<EntityType, Integer> counts = new EnumMap<>(EntityType.class);

    public WorldMap() {
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

            while (counts.get(type) < limit * BOARD_OF_COUNT) {
                spawnEntity(type, 1);
            }

        }

    }

    private void spawnEntity(EntityType type, int limit) {
        int count = 0;
        while (count < limit) {
            int x = (int) (Math.random() * height);
            int y = (int) (Math.random() * width);
            Coordinates coordinates = new Coordinates(x, y);
            if (entityMap.get(coordinates) == null) {
                EntityFactory factory = new EntityFactory();
                Entity entity = factory.createEntity(type, config, bfs);// создаём новый объект
                setEntity(coordinates, entity);
                count++;
            }
        }
    }


}

