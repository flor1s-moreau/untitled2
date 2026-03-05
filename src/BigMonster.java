import java.util.Scanner;

public class BigMonster extends Monster {
    private String image = "\ud83d\udc79";

    BigMonster(int sizeBoard) {
        super(sizeBoard);
        setImage(image);
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Решите задачу:");
        if (difficultGame == 1) {
            return super.taskMonster(1);
        } else {
            int x = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int y = r.nextInt(10 * (difficultGame - 1), 10 * difficultGame);
            int z = r.nextInt(100 * (difficultGame - 1), 100 * difficultGame);
            int trueAnswer = x * y - z;
            System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
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

    public boolean taskMonster() {
        return super.taskMonster(0);
    }
}