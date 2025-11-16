package models;

import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;


public class Predator extends Creature {
    private int power;

    public Predator(WorldMapConfig config) {
        super(config);
        this.sign = config.getPredatorSign();
    }

    @Override
    public void makeMove(Coordinates currentPosition, WorldMap world) {

    }
}
