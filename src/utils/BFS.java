package utils;

import models.Entity;
import world.WorldMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public Coordinates searchGoal(Coordinates currentPosition, WorldMap world, Class<?> goal) {

        int width = world.getWidth();
        int height = world.getHeight();
        Queue<Coordinates> toVisit = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();
        Entity searcher = world.getEntity(currentPosition);

        visited.add(currentPosition);
        toVisit.add(currentPosition);
        while (!toVisit.isEmpty()) {

            currentPosition = toVisit.poll();
            int x = currentPosition.getX();
            int y = currentPosition.getY();

            Entity cell = world.getEntity(currentPosition);
            if (goal.isInstance(cell)) {
                return currentPosition;

            } else {
                List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                        new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));


                for (Coordinates direction : directions) {
                    int directionX = direction.getX();
                    int directionY = direction.getY();

                        if (directionX >= 0 && directionY >= 0 && directionX < height && directionY < width) {
                            Coordinates neighbour = new Coordinates(directionX, directionY);
                            if (!visited.contains(neighbour)) {
                                visited.add(neighbour);
                                toVisit.add(neighbour);
                            }
                        }


                }
            }


        }

        return null; // неудача

    }

    public List<Coordinates> searchPath() {

        return null;
    }
}
