package utils;

import models.Entity;
import world.WorldMap;

import java.awt.*;
import java.util.*;
import java.util.List;

public class BFS {
    public Coordinates searchGoal(Coordinates currentPosition, WorldMap world, Class<?> goal) {

        int width = world.getWidth();
        int height = world.getHeight();
        Queue<Coordinates> toVisit = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();

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

    public List<Coordinates> searchPath(Coordinates startPoint, Coordinates finalPoint, WorldMap world) {

        int width = world.getWidth();
        int height = world.getHeight();
        Queue<Coordinates> toVisit = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        visited.add(startPoint);
        toVisit.add(startPoint);
        while (!toVisit.isEmpty()) {

            Coordinates currentPosition = toVisit.poll();
            int x = currentPosition.getX();
            int y = currentPosition.getY();

            if (finalPoint.equals(currentPosition)) {
                List<Coordinates> road = new ArrayList<>();
                road.add(currentPosition);
                while (!currentPosition.equals(startPoint)) {
                    currentPosition = parentMap.get(currentPosition);
                    road.add(currentPosition);
                }
                Collections.reverse(road);
                road.remove(0);
                return road;

            } else {
                List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                        new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));

                if (world.isCellEmpty(currentPosition) || currentPosition.equals(startPoint)) {
                    for (int i = 0; i < 4; i++) {
                        Random random = new Random();
                        Coordinates direction = directions.get(random.nextInt(directions.size()));
                        int directionX = direction.getX();
                        int directionY = direction.getY();

                        if (directionX >= 0 && directionY >= 0 && directionX < height && directionY < width) {
                            Coordinates neighbour = new Coordinates(directionX, directionY);
                            if (!visited.contains(neighbour)) {
                                visited.add(neighbour);
                                toVisit.add(neighbour);
                                if (!parentMap.containsKey(neighbour) && (world.isCellEmpty(neighbour) ||
                                        neighbour.equals(finalPoint))) {
                                    parentMap.put(neighbour, currentPosition);
                                }
                            }
                        }


                    }
                } else {

                }

            }

        }

        return null; // неудача

    }
}
