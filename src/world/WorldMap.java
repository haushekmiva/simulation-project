package world;

import models.*;
import models.herbivore.Herbivore;
import models.predator.Predator;
import utils.Coordinates;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class WorldMap {
    private int width;
    private int height;
    private int herbivoreLimit;
    private int predatorLimit;
    private int grassLimit;
    private int treeLimit;
    private int rockLimit;

    private Map<Coordinates, Entity> entityMap = new HashMap<>();
    private Map<Entity, Coordinates> coordinatesMap = new HashMap<>();


    private final Random random = new Random();

    public WorldMap(WorldMapConfig config) {
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.herbivoreLimit = config.getHerbivoreLimit();
        this.predatorLimit = config.getPredatorLimit();
        this.grassLimit = config.getGrassLimit();
        this.treeLimit = config.getTreeLimit();
        this.rockLimit = config.getRockLimit();

        spawnEntity(Predator.class, config, predatorLimit, 5);
        spawnEntity(Herbivore.class, config, herbivoreLimit, 5);
        spawnEntity(Grass.class, config, grassLimit, 5);
        spawnEntity(Rock.class, config, rockLimit, 5);
        spawnEntity(Tree.class, config, treeLimit, 5);


    }

    public Random getRandom() {
        return random;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
    }

    public void deleteEntity(Coordinates from) {
        Entity entity = entityMap.get(from);
        entityMap.remove(from);
        coordinatesMap.remove(entity);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        deleteEntity(from);
        setEntity(to, entity);
    }

    public Coordinates getEntityPosition(Entity entity) {
        return coordinatesMap.get(entity);
    }


    private void spawnEntity(Class<? extends Entity> clazz, WorldMapConfig config, int limit, int chance) {
        int count = 0;
        while (count < limit) {
            int x = (int) (Math.random() * height);
            int y = (int) (Math.random() * width);
            Coordinates coordinates = new Coordinates(x, y);
            if (entityMap.get(coordinates) == null) {
                if ((Math.random() < chance / 100.0)) {
                    try {
                        Entity entity = clazz.getDeclaredConstructor(WorldMapConfig.class).newInstance(config); // создаём новый объект
                        entityMap.put(coordinates, entity);
                        coordinatesMap.put(entity, coordinates);
                        count++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        }
    }


}

