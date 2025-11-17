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

        Scanner in = new Scanner(System.in);
        in.nextLine();

        actions.turnActions(world);
        Render.render(world);
        in.nextLine();

        actions.turnActions(world);
        Render.render(world);
        in.nextLine();


        actions.turnActions(world);
        Render.render(world);
        in.nextLine();


        actions.turnActions(world);
        Render.render(world);
        in.nextLine();


        actions.turnActions(world);
        Render.render(world);
        in.nextLine();


    }

    public void pauseSimulation() {

    }

}
