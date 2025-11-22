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
        this.health = config.getPredatorHealth();
        this.movesAfterTheLastMove = config.getPredatorMoveDelay();
        this.power = config.getPredatorPower();
        this.sign = config.getPredatorSign();
        this.moveDelay = config.getPredatorMoveDelay();
    }

    private void attack(Coordinates victimPosition, WorldMap world) {
        Entity entity = world.getEntity(victimPosition);
        if (entity instanceof Creature victim) {
            victim.reduceHealth(power);
            System.out.println("Somebody attacked somebody. The victim has " + victim.getHealth() + " now");
        }
    }

    @Override
    public void makeMove(WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(this);
        BFS bfs = new BFS();
        Coordinates goalPosition = bfs.searchGoal(currentPosition, world, Herbivore.class);


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
                        attack(nextMove, world);
                    }
                    world.moveEntity(currentPosition, nextMove);

                }




            }
        }
    }
}