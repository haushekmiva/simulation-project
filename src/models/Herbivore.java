package models;

import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;


public class Herbivore extends Creature {
    public Herbivore(WorldMapConfig config) {
        super(config);
        this.sign = config.getHerbivoreSign();
    }

    public void makeMove(Coordinates currentPosition, WorldMap map) {

    }
}
