package models.herbivore;

import models.Creature;
import models.Entity;
import models.Grass;
import utils.BFS;
import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.List;


public class Herbivore extends Creature {
    public Herbivore(WorldMapConfig config) {
        super(config);
        this.health = config.getHerbivoreHealth();
        this.maxHealth = config.getHerbivoreHealth();
        this.movesAfterTheLastMove = config.getHerbivoreMoveDelay();
        this.sign = config.getHerbivoreSign();
        this.moveDelay = config.getHerbivoreMoveDelay();
        this.onArriveBehavior = new HerbivoreOnArrive();
        this.target = Grass.class;
    }

}
