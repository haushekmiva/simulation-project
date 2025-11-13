package world;

import models.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private int herbivoreLimit;
    private int predatorLimit;
    private int grassLimit;
    private int treeLimit;
    private int rockLimit;

    private Map<Point, Entity> map = new HashMap<>();

    public WorldMap(int width, int height, int herbivoreLimit, int predatorLimit, int grassLimit,
                    int treeLimit, int rockLimit) { // генерация карты

    }

    public boolean isCellEmpty(Point cell) {
        return false;
    };

    public void setEntity(Point to, Entity entity) {

    }

    public void deleteEntity(Point from, Entity entity) {

    }

    public void moveEntity(Point from, Point to, Entity entity) {

    }
}
