package models.herbivore;

import models.Creature;
import models.EntityType;
import models.Grass;
import world.WorldMapConfig;


public class Herbivore extends Creature {

    public Herbivore(WorldMapConfig config) {
        super(config);
        this.type = EntityType.HERBIVORE;
        this.health = config.getHerbivoreHealth();
        this.maxHealth = config.getHerbivoreHealth();
        this.movesAfterTheLastMove = config.getHerbivoreMoveDelay();
        this.sign = config.getHerbivoreSign();
        this.moveDelay = config.getHerbivoreMoveDelay();
        this.onArriveBehavior = new HerbivoreOnArrive();
        this.target = Grass.class;

    }

}
