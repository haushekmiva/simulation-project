package utils;

import models.Entity;
import models.EntityType;
import world.WorldMap;

import java.util.*;

public class BFS {

    /**
     * This method searches for the target that the creature will move toward.
     *
     * @param currentPosition the creature's current position
     * @param world the world object from which we will take information
     * @param goal the type of entity that is the creature's target
     * @return the target coordinates or Coordinates.EMPTY if the target is not found
     */
    public Coordinates searchGoal(Coordinates currentPosition, WorldMap world, EntityType goal) {

        Queue<Coordinates> toVisit = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();

        visited.add(currentPosition);
        toVisit.add(currentPosition);

        // BFS loop
        while (!toVisit.isEmpty()) {

            currentPosition = toVisit.poll();
            Entity cell = world.getEntity(currentPosition);

            // if there's a creature in the cell (if it exists) and it's our goal, exit and return its coordinates
            if (cell != null && cell.getType() == goal) return currentPosition;

            int x = currentPosition.getX();
            int y = currentPosition.getY();

            List<Coordinates> directions =
                    CoordinatesUtils.determineDirections(x, y); // list of all neighboring cells (those sharing a border)


            for (Coordinates direction : directions) {
                int dx = direction.getX();
                int dy = direction.getY();

                // if the neighbor is outside the map, skip it
                if (!CoordinatesUtils.isInBoundaries(dx, dy, world)) continue;

                Coordinates neighbour = new Coordinates(dx, dy);

                // if we haven't visited the neighbor yet, add it to the queue to visit
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    toVisit.add(neighbour);
                }
            }
        }
        return Coordinates.EMPTY; // return empty coordinates if the target wasn't found
    }

    /**
     *
     *
     * @param startPoint the point from which the path is built
     * @param finalPoint the point to which the path is built
     * @param world the world object from which we will take information
     * @return the path or an empty list in case of failure
     */
    public List<Coordinates> searchPath(Coordinates startPoint, Coordinates finalPoint, WorldMap world) {

        // if the final point is undefined, exit returning an empty list
        if (finalPoint.isEmpty())
            return List.of();

        Queue<Coordinates> toVisit = new ArrayDeque<>();
        Set<Coordinates> visited = new HashSet<>();


        // Map cell -> parent. We'll use it to reconstruct the path to the target
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();

        visited.add(startPoint);
        toVisit.add(startPoint);

        // BFS loop
        while (!toVisit.isEmpty()) {

            Coordinates currentPosition = toVisit.poll();

            // if we've reached the target, return the road to it as a list
            if (finalPoint.equals(currentPosition))
                return generateRoad(currentPosition, startPoint, parentMap);

            int x = currentPosition.getX();
            int y = currentPosition.getY();
            List<Coordinates> directions = CoordinatesUtils.determineDirections(x, y);

            // if the cell is empty, then we do not search its neighbors (except when it's the start position)
            if (!world.isCellEmpty(currentPosition) && !currentPosition.equals(startPoint)) continue;

            for (Coordinates direction : directions) {

                int dx = direction.getX();
                int dy = direction.getY();

                // if the neighbor is outside the map, skip it
                if (!CoordinatesUtils.isInBoundaries(dx, dy, world)) continue;

                Coordinates neighbour = new Coordinates(dx, dy);
                if (visited.contains(neighbour)) continue;

                visited.add(neighbour);
                toVisit.add(neighbour);

                // add the cell to the map if it's not there yet and the cell is empty / is the final point
                if (!parentMap.containsKey(neighbour) && (world.isCellEmpty(neighbour) ||
                        neighbour.equals(finalPoint))) {
                    parentMap.put(neighbour, currentPosition);
                }
            }
        }


        return List.of(); // in case of not finding a path return an empty list

    }

    /**
     * This method generates a path from point A to point B using
     * the hash map cell->parent from the BFS search method that finds point B from point A
     *
     * @param finalPoint from this point we recreate the path
     * @param startPoint to this point we recreate the path
     * @param parentMap the cell->parent map
     * @return the path as a list
     */
    private List<Coordinates> generateRoad(Coordinates finalPoint, Coordinates startPoint, Map<Coordinates,
            Coordinates> parentMap) {

        Coordinates currentPosition = finalPoint;

        List<Coordinates> road = new ArrayList<>();
        road.add(currentPosition);

        // continue reconstructing the path from B to A until we reach A
        while (!currentPosition.equals(startPoint)) {
            currentPosition = parentMap.get(currentPosition);
            road.add(currentPosition);
        }


        Collections.reverse(road); // reverse the list because we built it from end to start
        road.removeFirst(); // remove the starting point because the creature is already on it
        return road;
    }

}
