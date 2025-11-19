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

    public void eat(Coordinates currentPosition, Coordinates foodPosition, WorldMap world) {
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

    public void makeMove(Coordinates currentPosition, WorldMap world) {
        BFS bfs = new BFS();
        Coordinates goalPosition = bfs.searchGoal(currentPosition, world, Grass.class);

        if (goalPosition == null) {
            makeRandomMove(currentPosition, world);
        } else {

            List<Coordinates> road = bfs.searchPath(currentPosition, goalPosition, world);

            if (road == null) {
                makeRandomMove(currentPosition, world);
            } else {
                Coordinates finalPoint = road.getLast();

                Coordinates nextMove = road.getFirst();
                if (!finalPoint.equals(currentPosition)) {
                    if (world.getEntity(nextMove) instanceof Herbivore) {
                        eat(nextMove, goalPosition, world);
                    }
                    world.moveEntity(currentPosition, nextMove);

                }




            }
        }
    }
}
