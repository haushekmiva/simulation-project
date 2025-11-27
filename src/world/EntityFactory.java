package world;

import models.*;
import models.herbivore.Herbivore;
import models.predator.Predator;
import utils.BFS;

public class EntityFactory {
    public Entity createEntity(EntityType type, WorldMapConfig config, BFS bfs) {
        return switch (type) {
            case HERBIVORE -> new Herbivore(config, bfs);
            case PREDATOR -> new Predator(config, bfs);
            case ROCK -> new Rock(config);
            case TREE -> new Tree(config);
            case GRASS -> new Grass(config);
        };
    }
}
