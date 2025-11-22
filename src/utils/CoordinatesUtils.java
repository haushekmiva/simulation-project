package utils;

import world.WorldMap;

public class CoordinatesUtils {

    public static boolean isInBoundaries(int x, int y, WorldMap world) {
        int width = world.getWidth();
        int height = world.getHeight();
        return (x >= 0 && y >= 0 && x < height && y < width);
    }
}
