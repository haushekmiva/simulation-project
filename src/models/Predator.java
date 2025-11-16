package models;

import utils.BFS;
import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.List;
import java.util.Scanner;


public class Predator extends Creature {
    private int power;

    public Predator(WorldMapConfig config) {
        super(config);
        this.sign = config.getPredatorSign();
    }

    @Override
    public void makeMove(Coordinates currentPosition, WorldMap world) {
        BFS bfs = new BFS();
        List<Coordinates> road = bfs.search(world, currentPosition, 5, Grass.class);
        System.out.println(road);
    }
}
