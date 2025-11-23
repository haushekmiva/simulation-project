package models;

import models.herbivore.Herbivore;
import utils.BFS;
import utils.Coordinates;
import utils.CoordinatesUtils;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Creature extends Entity {
    protected int speed;
    protected int maxHealth;
    protected int health;
    protected int moveDelay;
    protected int movesAfterTheLastMove = 0;
    protected OnArrive onArriveBehavior;
    protected Class<? extends Entity> target;
    public Creature(WorldMapConfig config, OnArrive onArriveBehavior) {
        super(config);
        this.onArriveBehavior = onArriveBehavior;
    }

    public Creature(WorldMapConfig config) {
        super(config);
    }

    public int getMoveDelay() {
        return moveDelay;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMovesAfterTheLastMove() {
        return movesAfterTheLastMove;
    }

    public void setMovesAfterTheLastMove(int value) {
        this.movesAfterTheLastMove = value;
    }

    public boolean canMove() {

        if (this instanceof Herbivore h) {
            System.out.println(movesAfterTheLastMove);
        }

        if (moveDelay <= movesAfterTheLastMove) {
            return true;
        }
        return false;
    }

    public void reduceHealth(int hp) {
        this.health -= hp;
    }

    protected void makeRandomMove(WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(this);

        int x = currentPosition.getX();
        int y = currentPosition.getY();

        List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));

        for (int i = 0; i < 4; i++) {

            Random random = world.getRandom();
            int randomIndex = random.nextInt(directions.size());
            Coordinates direction = directions.get(randomIndex);
            directions.remove(randomIndex);

            int directionX = direction.getX();
            int directionY = direction.getY();

            if (CoordinatesUtils.isInBoundaries(directionX, directionY, world)) {
                if (world.isCellEmpty(direction)) {
                    System.out.println(direction);
                    world.moveEntity(currentPosition, direction);
                    break;
                }

            }
        }
    }

    public void makeMove(WorldMap world) {

        if (canMove()) {

            Coordinates currentPosition = world.getEntityPosition(this);
            BFS bfs = new BFS();
            Coordinates goalPosition = bfs.searchGoal(currentPosition, world, target);

            if (goalPosition == Coordinates.EMPTY) {
                makeRandomMove(world);
            } else {

                List<Coordinates> road = bfs.searchPath(currentPosition, goalPosition, world);

                if (road.isEmpty()) {
                    makeRandomMove(world);
                } else {
                    Coordinates finalPoint = road.getLast();

                    Coordinates nextMove = road.getFirst();
                    if (!finalPoint.equals(currentPosition)) {
                        if (target.isInstance(world.getEntity(nextMove))) {
                            onArriveBehavior.onArrive(this, nextMove, world);
                        } else {
                            world.moveEntity(currentPosition, nextMove);
                        }
                    }

                }

            } movesAfterTheLastMove = 0;


        } else movesAfterTheLastMove += 1;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }
}
