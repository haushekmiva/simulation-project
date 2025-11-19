import utils.Actions;
import utils.Render;
import world.WorldMap;

import java.util.Scanner;


public class Simulation {
    private WorldMap map;
    private int turnCounter;
    private Actions actions = new Actions();

    public void nextTurn() {

    }

    public void startSimulation() {

        // начальная генерация
        WorldMap world = actions.initActions();
        Render.render(world);

        while (true) {
        actions.turnActions(world);
        Render.render(world);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
}



    }

    public void pauseSimulation() {

    }

}
