package models;

import utils.Coordinates;
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

    public int getMoveDelay() {
        return moveDelay;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMovesAfterTheLastMove() {
        return movesAfterTheLastMove;
    }

    public boolean canMove() {
        if (moveDelay <= movesAfterTheLastMove) {
            return true;
        } return false;
    }

    public Creature(WorldMapConfig config) {
        super(config);
    }

    public void setMovesAfterTheLastMove(int value) {
        this.movesAfterTheLastMove = value;
    }

    protected void reduceHealth(int hp) {
        this.health -= hp;
    }

    protected void makeRandomMove(WorldMap world) {
        Coordinates currentPosition = world.getEntityPosition(this);

        int x = currentPosition.getX();
        int y = currentPosition.getY();

        int height = world.getHeight();
        int width = world.getWidth();

        List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));

        for (int i = 0; i < 4; i++) {

            Random random = world.getRandom();
            int randomIndex = random.nextInt(directions.size());
            Coordinates direction = directions.get(randomIndex);
            directions.remove(randomIndex);

            int directionX = direction.getX();
            int directionY = direction.getY();

            if (directionX >= 0 && directionY >= 0 && directionX < height && directionY < width) {
                if  (world.isCellEmpty(direction)) {
                    System.out.println(direction);
                    world.moveEntity(currentPosition, direction);
                    break;
                }

            }
        }
    }

    public abstract void makeMove(WorldMap map);

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }
}
