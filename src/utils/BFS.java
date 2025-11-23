package utils;

import models.Entity;
import world.WorldMap;

import java.util.*;
import java.util.List;

public class BFS {
    public Coordinates searchGoal(Coordinates currentPosition, WorldMap world, Class<?> goal) {

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
                List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);


                for (Coordinates direction : directions) {
                    int directionX = direction.getX();
                    int directionY = direction.getY();

                        if (CoordinatesUtils.isInBoundaries(directionX, directionY, world)) {
                            Coordinates neighbour = new Coordinates(directionX, directionY);
                            if (!visited.contains(neighbour)) {
                                visited.add(neighbour);
                                toVisit.add(neighbour);
                            }
                        }


                }
            }


        }

        return Coordinates.EMPTY; // неудача

    }

    public List<Coordinates> searchPath(Coordinates startPoint, Coordinates finalPoint, WorldMap world) {

        if (finalPoint.isEmpty())
            return List.of();

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
                List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);
                if (world.isCellEmpty(currentPosition) || currentPosition.equals(startPoint)) {
                    for (int i = 0; i < 4; i++) {
                        Random random = new Random();
                        Coordinates direction = directions.get(random.nextInt(directions.size()));
                        int directionX = direction.getX();
                        int directionY = direction.getY();

                        if (CoordinatesUtils.isInBoundaries(directionX, directionY, world)) {
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

        return List.of(); // неудача

    }
}
