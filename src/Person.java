public class Person {
    private int x, y;
    private int live;
    private String image = "\ud83e\uddd9\u200d";

    public Person(int sizeBoard) {
        this.x = 1;
        this.y = 1;
        this.live = 5;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getLive() { return live; }
    public String getImage() { return image; }

    public void move(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public boolean moveCorrect(int newX, int newY) {
        int dx = Math.abs(this.x - newX);
        int dy = Math.abs(this.y - newY);
        return (dx == 1 && dy == 0) || (dx == 0 && dy == 1);
    }

    public void downLive() {
        live--;
    }

    public void upLive() {
        live++;
    }
}