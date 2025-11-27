import world.WorldMapConfig;

public class Main {
    public static void main(String[] args) {
        WorldMapConfig config = new WorldMapConfig();
        Simulation simulation = new Simulation();
        simulation.startSimulation(config);
        }
    }

/*
 * добавить логирование продуманное
 * добавить умную генерацию мира чтобы был распределенный спавн
 * задокументировать код (в процессе)
 * */
