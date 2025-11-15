import utils.Actions;
import utils.Render;
import world.WorldMap;
import world.WorldMapConfig;



public class Simulation {
    private WorldMap map;
    private int turnCounter;
    private Actions actions = new Actions();

    public void nextTurn() {

    }

    public void startSimulation() {

        // начальная генерация
        WorldMapConfig config = new WorldMapConfig();
        WorldMap world = new WorldMap(config);
        actions.initActions(world);
        Render.render(world);

    }

    public void pauseSimulation() {

    }

}
