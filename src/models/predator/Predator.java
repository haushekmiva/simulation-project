package models.predator;

import models.Creature;
import models.Entity;
import models.herbivore.Herbivore;
import world.WorldMapConfig;


public class Predator extends Creature {
    private int power;

    public Predator(WorldMapConfig config) {
        super(config);
        this.health = config.getPredatorHealth();
        this.movesAfterTheLastMove = config.getPredatorMoveDelay();
        this.power = config.getPredatorPower();
        this.sign = config.getPredatorSign();
        this.moveDelay = config.getPredatorMoveDelay();
        this.target = Herbivore.class;
        this.onArriveBehavior = new PredatorOnArrive(power);
    }

    public int getPower() {
        return power;
    }
}