package utils;

import models.Entity;
import world.WorldMap;

import java.util.*;

public class BFS {

    public List<Coordinates> search(WorldMap world, Coordinates startPoint, int depth, Class<?> goal) {
        Queue<Coordinates> toVisit = new ArrayDeque<>();
        List<Coordinates> visited = new ArrayList<>();
        Map<Coordinates, Coordinates> parentMap = new HashMap<>();
        visited.add(startPoint);
        toVisit.add(startPoint);
        while (!toVisit.isEmpty()) {

            Coordinates currentPosition = toVisit.poll();
            visited.add(currentPosition);
            int x = currentPosition.getX();
            int y = currentPosition.getY();

            if (visited.contains(currentPosition)) continue;
            else if (world.getEntity(currentPosition).getClass() == goal) {
                return getRoad(parentMap, currentPosition, startPoint);
            } else {


                Coordinates neighbour;


                neighbour = new Coordinates(x + 1, y);
                if (getDepth(parentMap, neighbour, startPoint) < depth) {
                    toVisit.add(neighbour);
                    if (!parentMap.containsKey(neighbour)) {
                        parentMap.put(neighbour, currentPosition);
                    }
                }


                neighbour = new Coordinates(x - 1, y);
                if (getDepth(parentMap, neighbour, startPoint) < depth) {
                    toVisit.add(neighbour);
                    if (!parentMap.containsKey(neighbour)) {
                        parentMap.put(neighbour, currentPosition);
                    }
                }


                neighbour = new Coordinates(x, y + 1);
                if (getDepth(parentMap, neighbour, startPoint) < depth) {
                    toVisit.add(neighbour);
                    if (!parentMap.containsKey(neighbour)) {
                        parentMap.put(neighbour, currentPosition);
                    }
                }


                neighbour = new Coordinates(x, y - 1);
                if (getDepth(parentMap, neighbour, startPoint) < depth) {
                    toVisit.add(neighbour);
                    if (!parentMap.containsKey(neighbour)) {
                        parentMap.put(neighbour, currentPosition);
                    }
                }

            }
        }
        return null;
    }

    private List<Coordinates> getRoad(Map<Coordinates, Coordinates> parentMap, Coordinates currentPosition, Coordinates startPoint) {
        Coordinates parent = null;
        Coordinates neighbour;
        List<Coordinates> road = new ArrayList<>();
        road.add(currentPosition);

        while (parent != startPoint) {
            parent = parentMap.get(currentPosition);
            road.add(parent);
        }

        return road;
    }

    private int getDepth(Map<Coordinates, Coordinates> parentMap, Coordinates currentPosition, Coordinates startPoint) {
        return getRoad(parentMap, currentPosition, startPoint).size();
    }
}
