import java.util.Random;

public class Teleport {
    private int x, y;
    private final String image = "🌀";
    private boolean isActive = true;

    public Teleport(int boardSize) {
        Random r = new Random();
        this.x = r.nextInt(boardSize) + 1;
        this.y = r.nextInt(boardSize) + 1;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getImage() { return image; }
    public boolean isActive() { return isActive; }
    public void deactivate() { isActive = false; }

    public boolean isPlayerOnTeleport(int playerX, int playerY) {
        return isActive && playerX == x && playerY == y;
    }

    public static int[] findRandomEmptyCell(String[][] board, int boardSize, int excludeX, int excludeY) {
        Random r = new Random();
        int attempts = 0;
        int maxAttempts = 1000;

        while (attempts < maxAttempts) {
            int newX = r.nextInt(boardSize) + 1;
            int newY = r.nextInt(boardSize) + 1;

            if (newX == excludeX && newY == excludeY) {
                attempts++;
                continue;
            }

            if (board[newY - 1][newX - 1].equals("  ")) {
                return new int[]{newX, newY};
            }
            attempts++;
        }

        return new int[]{excludeX, excludeY};
    }
}