package utils;

import world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesUtils {

    public static boolean isInBoundaries(int x, int y, WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        return (x >= 0 && y >= 0 && x < height && y < width);
    }

    public static List<Coordinates> determineDirections(int x, int y) {
        List<Coordinates> directions = new ArrayList<>(List.of(new Coordinates(x + 1, y),
                new Coordinates(x - 1, y), new Coordinates(x, y + 1), new Coordinates(x, y - 1)));

        return directions;
    }

}
