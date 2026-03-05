import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\ud83e\udddf\u200d";
    private int x, y;
    Random r = new Random();

    Monster(int sizeBoard) {
        // координаты от 1 до sizeBoard
        this.x = r.nextInt(sizeBoard) + 1;
        this.y = r.nextInt(sizeBoard) + 1;
    }

    public String getImage() { return image; }
    public int getX() { return x; }
    public int getY() { return y; }

    public boolean conflictPerson(int perX, int perY) {
        return perX == this.x && perY == this.y;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Решите задачу:");
        int a = r.nextInt(100);
        int b = r.nextInt(100);
        int trueAnswer = a + b;
        System.out.println("Реши пример: " + a + " + " + b + " = ?");
        Scanner sc = new Scanner(System.in);
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            return true;
        } else {
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }
}