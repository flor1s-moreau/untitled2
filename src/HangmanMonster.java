import java.util.Random;
import java.util.Scanner;

public class HangmanMonster extends Monster {

    private String[] words = {"java", "программа", "компьютер", "игра", "код", "монстр"};
    private String[] hints = {"язык", "что пишут", "устройство", "во что играют", "что пишут", "кто здесь"};

    public HangmanMonster(int boardSize) {
        super(boardSize);
        this.setImage("🎮");
    }

    @Override
    public boolean taskMonster(int difficulty) {
        Random r = new Random();
        int index = r.nextInt(words.length);
        String word = words[index];
        String hint = hints[index];

        return playHangman(word, hint);
    }

    private boolean playHangman(String word, String hint) {
        Scanner sc = new Scanner(System.in);
        StringBuilder guessed = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            guessed.append("_");
        }

        int attempts = 6;
        boolean wordGuessed = false;

        System.out.println("🎮 Виселица! Подсказка: " + hint);
        System.out.println("Слово: " + guessed);

        while (attempts > 0 && !wordGuessed) {
            System.out.println("Осталось попыток: " + attempts);
            System.out.print("Введите букву или слово целиком: ");
            String input = sc.nextLine().toLowerCase();

            if (input.length() == 1) {
                char letter = input.charAt(0);
                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter && guessed.charAt(i) == '_') {
                        guessed.setCharAt(i, letter);
                        found = true;
                    }
                }
                if (!found) {
                    attempts--;
                    System.out.println("❌ Нет такой буквы!");
                } else {
                    System.out.println("✅ Есть такая буква!");
                }
            } else {
                if (input.equals(word)) {
                    wordGuessed = true;
                    break;
                } else {
                    attempts--;
                    System.out.println("❌ Неверное слово!");
                }
            }

            System.out.println("Слово: " + guessed);
            if (guessed.indexOf("_") == -1) {
                wordGuessed = true;
            }
        }

        if (wordGuessed) {
            System.out.println("🎉 Вы угадали слово! Монстр побеждён!");
            return true;
        } else {
            System.out.println("💀 Вы не угадали. Монстр атакует!");
            return false;
        }
    }
}