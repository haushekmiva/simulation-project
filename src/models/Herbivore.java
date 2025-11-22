package models;

import utils.BFS;
import utils.Coordinates;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.List;
import java.util.Scanner;


public class Herbivore extends Creature {
    public Herbivore(WorldMapConfig config) {
        super(config);
        this.health = config.getHerbivoreHealth();
        this.maxHealth = config.getHerbivoreHealth();
        this.movesAfterTheLastMove = config.getHerbivoreMoveDelay();
        this.sign = config.getHerbivoreSign();
        this.moveDelay = config.getHerbivoreMoveDelay();
    }

    public void eat(Coordinates foodPosition, WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(this);

        world.deleteEntity(foodPosition);
        world.moveEntity(currentPosition, foodPosition);
        Entity entity = world.getEntity(foodPosition);
        if (entity instanceof Grass food) {
        if (health < maxHealth) {
            health += food.getEnergy();
        }

        if (health > maxHealth) health = maxHealth;
        }
    }

    @Override
    public void makeMove(WorldMap world) {

        Coordinates currentPosition = world.getEntityPosition(this);
        BFS bfs = new BFS();
        Coordinates goalPosition = bfs.searchGoal(currentPosition, world, Grass.class);

        if (goalPosition == null) {
            makeRandomMove(world);
        } else {

            List<Coordinates> road = bfs.searchPath(currentPosition, goalPosition, world);

            if (road == null) {
                makeRandomMove(world);
            } else {
                Coordinates finalPoint = road.getLast();

                Coordinates nextMove = road.getFirst();
                if (!finalPoint.equals(currentPosition)) {
                    if (world.getEntity(nextMove) instanceof Herbivore) {
                        eat(goalPosition, world);
                    }
                    world.moveEntity(currentPosition, nextMove);

                }




            }
        }
    }
}
