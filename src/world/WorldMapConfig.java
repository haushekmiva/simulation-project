package world;

public class WorldMapConfig {
    private final int width = 25;
    private final int height = 10;

    private final int herbivoreLimit = 8;
    private final int predatorLimit = 3;
    private final int grassLimit = 20;
    private final int treeLimit = 5;
    private final int rockLimit = 10;

    private int herbivoreHealth = 4;
    private int predatorHealth = 16;
    private int predatorPower = 2;

    public int getPredatorHealth() {
        return predatorHealth;
    }

    private int predatorMoveDelay = 0;
    private int herbivoreMoveDelay = 1;

    private int grassEnergy = 2;

    public int getGrassEnergy() {
        return grassEnergy;
    }

    public int getHerbivoreHealth() {
        return herbivoreHealth;
    }

    public int getPredatorMoveDelay() {
        return predatorMoveDelay;
    }

    public int getHerbivoreMoveDelay() {
        return herbivoreMoveDelay;
    }

    public int getPredatorPower() {
        return predatorPower;
    }

    public String getEmptySign() {
        return emptySign;
    }

    private String emptySign = "⬛";
    private String predatorSign = "\uD83D\uDC3A";
    private String herbivoreSign = "\uD83D\uDC07";
    private String treeSign = "\uD83C\uDF38";
    private String grassSign = "\uD83C\uDF40";
    private String rockSign = "⛰\uFE0F";

    public int getWidth() {
        return width;
    }

    public String getRockSign() {
        return rockSign;
    }

    public String getGrassSign() {
        return grassSign;
    }

    public String getTreeSign() {
        return treeSign;
    }

    public String getHerbivoreSign() {
        return herbivoreSign;
    }

    public int getRockLimit() {
        return rockLimit;
    }

    public int getTreeLimit() {
        return treeLimit;
    }

    public int getPredatorLimit() {
        return predatorLimit;
    }

    public int getHerbivoreLimit() {
        return herbivoreLimit;
    }

    public int getHeight() {
        return height;
    }

    public int getGrassLimit() {
        return grassLimit;
    }

    public String getPredatorSign() {
        return predatorSign;
    }
}
