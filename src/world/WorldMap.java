package world;

import models.*;
import models.herbivore.Herbivore;
import models.predator.Predator;
import utils.Coordinates;

import java.util.*;


public class WorldMap {
    private WorldMapConfig config = new WorldMapConfig();

    private int width;
    private int height;

    private Map<Coordinates, Entity> entityMap = new HashMap<>();
    private Map<Entity, Coordinates> coordinatesMap = new HashMap<>();

    Map<Class<? extends Entity>, Integer> limits;
    Map<Class<? extends Entity>, Integer> counts = new HashMap<>(Map.of(
            Herbivore.class, 0,
            Predator.class, 0,
            Rock.class, 0,
            Grass.class, 0,
            Tree.class, 0
    ));


    private final Random random = new Random();

    public WorldMap() {
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.limits = new HashMap<>(Map.of(
                Herbivore.class, config.getHerbivoreLimit(),
                Predator.class, config.getPredatorLimit(),
                Rock.class, config.getRockLimit(),
                Grass.class, config.getGrassLimit(),
                Tree.class, config.getTreeLimit()
        ));

        spawnEntity(Predator.class,limits.get(Predator.class));
        spawnEntity(Herbivore.class, limits.get(Herbivore.class));
        spawnEntity(Grass.class, limits.get(Grass.class));
        spawnEntity(Rock.class,  limits.get(Rock.class));
        spawnEntity(Tree.class,  limits.get(Tree.class));


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

    private void changeCountOfEntity(Entity entity, int value) {

        if (entity instanceof Predator p) counts.put(Predator.class, counts.get(Predator.class) + value);
        if (entity instanceof Herbivore h) counts.put(Herbivore.class, counts.get(Herbivore.class) + value);
        if (entity instanceof Tree t) counts.put(Tree.class, counts.get(Tree.class) + value);
        if (entity instanceof Rock r) counts.put(Rock.class, counts.get(Rock.class) + value);
        if (entity instanceof Grass g) counts.put(Grass.class, counts.get(Grass.class) + value);


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
        List<Class<? extends Entity>> entitiesToBalance = new ArrayList<>(List.of(Herbivore.class, Grass.class));

        for (Class<? extends Entity> entityToBalance : entitiesToBalance) {
            int limit = limits.get(entityToBalance);

            while (counts.get(entityToBalance) < limit*0.3) {
                spawnEntity(entityToBalance, 1);
            }

        }

    }

    private void spawnEntity(Class<? extends Entity> clazz, int limit) {
        int count = 0;
        while (count < limit) {
            int x = (int) (Math.random() * height);
            int y = (int) (Math.random() * width);
            Coordinates coordinates = new Coordinates(x, y);
            if (entityMap.get(coordinates) == null) {
                try {
                    Entity entity = clazz.getDeclaredConstructor(WorldMapConfig.class).newInstance(config); // создаём новый объект
                    setEntity(coordinates, entity);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}

