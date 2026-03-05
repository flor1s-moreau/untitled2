import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "\ud83c\udff0";
        int sizeBoard = 5;
        Person person = new Person(sizeBoard);
        int step = 0;
        String[][] board = new String[sizeBoard][sizeBoard];

        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        // Монстры
        int countMonster = sizeBoard * sizeBoard - sizeBoard - 8; // чуть меньше, чтобы оставить место аптечкам
        Random r = new Random();
        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;

        while (count <= countMonster) {
            Monster test;
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                if (r.nextBoolean()) {
                    test = new HangmanMonster(sizeBoard); // добавляем виселицу
                } else {
                    test = new Monster(sizeBoard);
                }
            }

            if (board[test.getY() - 1][test.getX() - 1].equals("  ")) {
                board[test.getY() - 1][test.getX() - 1] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        // Аптечки
        int countPacks = 3;
        HealthPack[] healthPacks = new HealthPack[countPacks];
        for (int i = 0; i < countPacks; i++) {
            HealthPack hp = new HealthPack(sizeBoard);
            if (board[hp.getY() - 1][hp.getX() - 1].equals("  ")) {
                board[hp.getY() - 1][hp.getX() - 1] = hp.getImage();
                healthPacks[i] = hp;
            } else {
                i--; // повтор, если место занято
            }
        }

        // Замок
        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;
        board[castleY][castleX] = castle;

        Scanner sc = new Scanner(System.in);
        System.out.println("Привет! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры (от 1 до 5):");
                int difficultGame = sc.nextInt();

                while (person.getLive() > 0) {
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());

                    System.out.println("Введите куда будет ходить персонаж (ход по вертикали/горизонтали на 1 клетку)");
                    System.out.println("Координаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + ")");
                    int x = sc.nextInt();
                    int y = sc.nextInt();

                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];

                        // Проверка аптечек
                        for (HealthPack hp : healthPacks) {
                            if (hp != null && hp.isPlayerOnPack(x, y)) {
                                person.upLive();
                                hp.collect();
                                board[y - 1][x - 1] = "  ";
                                System.out.println("❤️ Вы подобрали аптечку! +1 жизнь");
                                break;
                            }
                        }

                        if (next.equals("  ") || next.equals("💊")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Ход корректный; Ход номер: " + step);
                        } else if (next.equals(castle)) {
                            System.out.println("🏆 Вы прошли игру!");
                            return;
                        } else {
                            // Встреча с монстром
                            for (Monster monster : arrMonster) {
                                if (monster != null && monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                    } else {
                                        person.downLive();
                                        System.out.println("💔 Осталось жизней: " + person.getLive());
                                    }
                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("❌ Некорректный ход");
                    }

                    if (person.getLive() <= 0) {
                        System.out.println("☠️ Игра окончена! Вы проиграли.");
                        break;
                    }
                }
                break;

            case "НЕТ":
                System.out.println("Жаль, приходи еще!");
                break;

            default:
                System.out.println("Данные введены некорректно");
        }
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + live + "\n");
    }
}