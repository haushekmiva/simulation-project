package models;

import utils.Coordinates;
import world.WorldMap;

public interface OnArrive {
    public void onArrive(Creature self, Coordinates targetPosition, WorldMap world);
}
