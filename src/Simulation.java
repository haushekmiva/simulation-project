import utils.Actions;
import utils.Render;
import world.WorldMap;
import world.WorldMapConfig;


public class Simulation {
    private Actions actions = new Actions();
    private WorldMapConfig config = new WorldMapConfig();
    private static final int TICK_TIME = 1000;

    public void startSimulation() {

        // начальная генерация
        WorldMap world = actions.initActions();
        Render.render(world, config);

        while (true) {
        actions.turnActions(world);
        Render.render(world, config);
            try {
                Thread.sleep(TICK_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Simulation interrupted");
            }
}


    }
}
