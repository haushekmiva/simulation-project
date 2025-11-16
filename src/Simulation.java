import utils.Actions;
import utils.Render;
import world.WorldMap;


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

    }

    public void pauseSimulation() {

    }

}
