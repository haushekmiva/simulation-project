package models;

import utils.Coordinates;
import world.WorldMap;

public interface OnArrive {
    void onArrive(Creature self, Coordinates targetPosition, WorldMap world);
}
