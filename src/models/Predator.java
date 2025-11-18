package models;

import utils.BFS;
import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.List;


public class Predator extends Creature {
    private int power;

    public Predator(WorldMapConfig config) {
        super(config);
        this.sign = config.getPredatorSign();
    }

    private void randomMove() {

    }

    @Override
    public void makeMove(Coordinates currentPosition, WorldMap world) {
        int cellsPerTurn = 2;
        BFS bfs = new BFS();
        Coordinates goalPosition = bfs.searchGoal(currentPosition, world, Herbivore.class);

        if (goalPosition == null) {
            System.out.println("нету жрачки пиздец");
            // рандом ход

        } else {

        List<Coordinates> road  = bfs.searchPath(currentPosition, goalPosition, world);
        Coordinates finalPoint = road.get(road.size() - 1);
        System.out.println(road);

        if (road == null) {
            System.out.println("типо двигается рандомно");
            // реализовать движение рандомные движения если не находистя цель
        }

        for (int i = 0; i < cellsPerTurn; i++) {


            if (finalPoint.equals(currentPosition)) break;

            world.moveEntity(currentPosition, road.get(i));

            currentPosition = road.get(i);
        }


    }
}}
