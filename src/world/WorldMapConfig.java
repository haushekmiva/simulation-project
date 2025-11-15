package world;

public class WorldMapConfig {
    private int width = 25;
    private int height = 10;
    //private int objectsCount = 5;

    private int herbivoreLimit = 5;
    private int predatorLimit = 2;
    private int grassLimit = 8;
    private int treeLimit = 5;
    private int rockLimit = 10;

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
