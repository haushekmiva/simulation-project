package utils;

import models.Entity;
import world.WorldMap;

import java.util.Map;

public class Render {
    // реализовать рендер чтобы попробовать
    public static void render(WorldMap world) {
        Map<Coordinates, Entity> worldMap = world.getWorldMap();
        int width = world.getWidth();
        int height = world.getHeight();
        for (int x = 0; x < height; x++) {
            System.out.print("\n");
            for (int y = 0; y < width; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                Entity cellContent = worldMap.get(coordinates);

                if (cellContent == null) System.out.print("⬛");
                else System.out.print(cellContent.getSign());
                System.out.print(" ");
            }

        }
    }

}
