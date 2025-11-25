package models.predator;

import models.Creature;
import models.Entity;
import models.OnArrive;
import utils.Coordinates;
import world.WorldMap;

public class PredatorOnArrive implements OnArrive {
    private final int power;

    public PredatorOnArrive(int power) {
        this.power = power;
    }

    @Override
    public void onArrive(Creature self, Coordinates victimPosition, WorldMap world) {
            Entity entity = world.getEntity(victimPosition);
            if (entity instanceof Creature victim) {
                victim.reduceHealth(power);
                System.out.println("Somebody attacked somebody. The victim has " + victim.getHealth() + " now");

        }
    }
}

