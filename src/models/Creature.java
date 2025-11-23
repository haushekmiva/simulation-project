package models;

import utils.BFS;
import utils.Coordinates;
import utils.CoordinatesUtils;
import world.WorldMap;
import world.WorldMapConfig;

import java.util.List;

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

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean canMove() {
        return moveDelay <= movesAfterTheLastMove;
    }

    public void reduceHealth(int hp) {
        this.health -= hp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected void makeRandomMove(WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(this);

        int x = currentPosition.getX();
        int y = currentPosition.getY();
        List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);

        for (Coordinates direction : directions) {
            int dx = direction.getX();
            int dy = direction.getY();

            if (!CoordinatesUtils.isInBoundaries(dx, dy, world))
                continue;

            if (!world.isCellEmpty(direction))
                continue;

            world.moveEntity(currentPosition, direction);
            break;

        }
    }


    public void makeMove(WorldMap world) {

        if (!canMove()) {
            movesAfterTheLastMove ++;
            return;
        }

        movesAfterTheLastMove = 0;

        BFS bfs = new BFS();
        Coordinates currentPosition = world.getEntityPosition(this);
        Coordinates goalPosition = bfs.searchGoal(currentPosition, world, target);
        List<Coordinates> road = bfs.searchPath(currentPosition, goalPosition, world);

        if (goalPosition == Coordinates.EMPTY || road.isEmpty()) {
            makeRandomMove(world);
            return;
        }

        if (road.getLast().equals(currentPosition)) {
            return;
        }

        Coordinates nextMove = road.getFirst();

        if (target.isInstance(world.getEntity(nextMove))) {
            onArriveBehavior.onArrive(this, nextMove, world);
        } else {
            world.moveEntity(currentPosition, nextMove);
        }


    }
}
