import java.util.Random;

public class HealthPack {
    private int x, y;
    private final String image = "💊";
    private boolean isActive = true;

    public HealthPack(int boardSize) {
        Random r = new Random();
        this.x = r.nextInt(boardSize) + 1;
        this.y = r.nextInt(boardSize) + 1;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getImage() { return image; }
    public boolean isActive() { return isActive; }
    public void collect() { isActive = false; }

    public boolean isPlayerOnPack(int playerX, int playerY) {
        return isActive && playerX == x && playerY == y;
    }
}