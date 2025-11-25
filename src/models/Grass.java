package models;

import world.WorldMapConfig;

public class Grass extends Entity {
    private int energy;

    public Grass(WorldMapConfig config) {

        super(config);
        this.type = EntityType.GRASS;
        this.energy = config.getGrassEnergy();
        this.sign = config.getGrassSign();
    }

    public int getEnergy() {
        return energy;
    }
}
