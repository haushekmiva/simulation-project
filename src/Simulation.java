import utils.Actions;
import utils.Render;
import world.WorldMap;
import world.WorldMapConfig;


public class Simulation {
    private Actions actions = new Actions();
    private static final int TICK_TIME = 1000;

    /**
     * Starts an infinite simulation loop.
     * Initializes the world and repeatedly performs turns, rendering the world
     * after each turn with a fixed tick delay.
     *
     * @param config the configuration of the world
     */
    public void startSimulation(WorldMapConfig config) {

        // Initialize the world
        WorldMap world = actions.initActions(config);
        Render.render(world, config);

        // Infinite simulation loop
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
