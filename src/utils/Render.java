package utils;

import models.Entity;
import world.WorldMap;
import world.WorldMapConfig;


public class Render {
    // реализовать рендер чтобы попробовать
    public static void render(WorldMap world) {
        WorldMapConfig config = new WorldMapConfig();
        int width = world.getWidth();
        int height = world.getHeight();
        for (int x = 0; x < height; x++) {
            System.out.print("\n");
            for (int y = 0; y < width; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity entity = world.getEntity(coordinates);
                if (entity != null) {
                    System.out.print(entity.getSign());
                } else System.out.print(config.getEmptySign());
                System.out.print(" ");
            }

        }
    }

}
