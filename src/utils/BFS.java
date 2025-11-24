package utils;

import models.Entity;
import world.WorldMap;

import java.util.*;

public class BFS {
    public Coordinates searchGoal(Coordinates currentPosition, WorldMap world, Class<?> goal) {

        Queue<Coordinates> toVisit = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();

        visited.add(currentPosition);
        toVisit.add(currentPosition);
        while (!toVisit.isEmpty()) {

            currentPosition = toVisit.poll();
            Entity cell = world.getEntity(currentPosition);

            if (goal.isInstance(cell)) return currentPosition;

            int x = currentPosition.getX();
            int y = currentPosition.getY();
            List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);

            for (Coordinates direction : directions) {
                int dx = direction.getX();
                int dy = direction.getY();

                if (!CoordinatesUtils.isInBoundaries(dx, dy, world)) continue;

                Coordinates neighbour = new Coordinates(dx, dy);
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    toVisit.add(neighbour);
                }
            }
        }
        return Coordinates.EMPTY; // неудача
    }

    public List<Coordinates> searchPath(Coordinates startPoint, Coordinates finalPoint, WorldMap world) {

        if (finalPoint.isEmpty())
            return List.of();

        Queue<Coordinates> toVisit = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        visited.add(startPoint);
        toVisit.add(startPoint);
        while (!toVisit.isEmpty()) {

            Coordinates currentPosition = toVisit.poll();

            if (finalPoint.equals(currentPosition))
                return generateRoad(currentPosition, startPoint, finalPoint, parentMap);

            int x = currentPosition.getX();
            int y = currentPosition.getY();
            List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);

            if (!world.isCellEmpty(currentPosition) && !currentPosition.equals(startPoint)) continue;

            for (Coordinates direction : directions) {

                int dx = direction.getX();
                int dy = direction.getY();

                if (!CoordinatesUtils.isInBoundaries(dx, dy, world)) continue;

                Coordinates neighbour = new Coordinates(dx, dy);
                if (visited.contains(neighbour)) continue;

                visited.add(neighbour);
                toVisit.add(neighbour);

                if (!parentMap.containsKey(neighbour) && (world.isCellEmpty(neighbour) ||
                        neighbour.equals(finalPoint))) {
                    parentMap.put(neighbour, currentPosition);
                }
            }
        }


        return List.of(); // неудача

    }

    private List<Coordinates> generateRoad(Coordinates currentPosition, Coordinates startPoint,
                                           Coordinates finalPoint, Map<Coordinates, Coordinates> parentMap) {

        List<Coordinates> road = new ArrayList<>();
        road.add(currentPosition);
        while (!currentPosition.equals(startPoint)) {
            currentPosition = parentMap.get(currentPosition);
            road.add(currentPosition);
        }
        Collections.reverse(road);
        road.removeFirst();
        return road;
    }

}
