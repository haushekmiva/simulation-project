package models.predator;

import models.Creature;
import models.EntityType;
import models.herbivore.Herbivore;
import utils.BFS;
import world.WorldMapConfig;


public class Predator extends Creature {
    private final int power;

    public Predator(WorldMapConfig config, BFS bfs) {
        super(config, bfs);
        this.type = EntityType.PREDATOR;
        this.health = config.getPredatorHealth();
        this.movesAfterTheLastMove = config.getPredatorMoveDelay();
        this.power = config.getPredatorPower();
        this.sign = config.getPredatorSign();
        this.moveDelay = config.getPredatorMoveDelay();
        this.target = EntityType.HERBIVORE;
        this.onArriveBehavior = new PredatorOnArrive(power);
    }

    public int getPower() {
        return power;
    }
}