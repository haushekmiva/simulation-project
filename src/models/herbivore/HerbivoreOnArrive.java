package models.herbivore;

import models.Creature;
import models.Entity;
import models.Grass;
import models.OnArrive;
import utils.Coordinates;
import world.WorldMap;

public class HerbivoreOnArrive implements OnArrive {
    @Override
    public void onArrive(Creature self, Coordinates foodPosition, WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(self);

        Entity entity = world.getEntity(foodPosition);
        world.deleteEntity(foodPosition);
        world.moveEntity(currentPosition, foodPosition);
        int health = self.getHealth();
        int maxHealth =  self.getMaxHealth();
        if (entity instanceof Grass food) {
            if (health < maxHealth) {
                self.setHealth(health + food.getEnergy());
            }

            if (health > maxHealth) self.setHealth(maxHealth);
        }
    }
}
