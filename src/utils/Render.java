package utils;

import models.Entity;
import world.WorldMap;
import world.WorldMapConfig;


public class Render {
    private static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private static void print(String s) {
        System.out.print(s);
    }

    public static void render(WorldMap world) {
        clearScreen();

        WorldMapConfig config = new WorldMapConfig();
        int width = world.getWidth();
        int height = world.getHeight();

        for (int x = 0; x < height; x++) {
            print("\n");
            for (int y = 0; y < width; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity entity = world.getEntity(coordinates);
                if (entity == null) {
                    print(config.getEmptySign());
                } else print(entity.getSign());
                print(" ");
            }

        }
    }

}
