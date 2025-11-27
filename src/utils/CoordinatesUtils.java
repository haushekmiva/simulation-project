package utils;

import world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesUtils {

    /**
     * Checks if the given point lies within the boundaries of the map.
     *
     * @param x the X coordinate of the point
     * @param y the Y coordinate of the point
     * @param world the world object representing the map
     * @return true if the point is inside the map boundaries, false otherwise
     */
    public static boolean isInBoundaries(int x, int y, WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        return (x >= 0 && y >= 0 && x < height && y < width);
    }

    /**
     * Creates and returns a list of neighboring coordinates (4 directions)
     * for a given cell.
     *
     * @param x the X coordinate of the cell
     * @param y the Y coordinate of the cell
     * @return a list of Coordinates representing the neighboring cells
     */
    public static List<Coordinates> determineDirections(int x, int y) {
        List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));

        return directions;
    }

}
