package world;

import models.*;
import models.herbivore.Herbivore;
import models.predator.Predator;

public class EntityFactory {
    public Entity createEntity(EntityType type, WorldMapConfig config) {
        return switch (type) {
            case HERBIVORE -> new Herbivore(config);
            case PREDATOR -> new Predator(config);
            case ROCK -> new Rock(config);
            case TREE -> new Tree(config);
            case GRASS -> new Grass(config);
        };
    }
}
