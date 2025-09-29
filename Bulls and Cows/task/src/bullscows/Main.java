package bullscows;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Input the length of the secret code:");
        String lengthInput = sc.nextLine();
        int length;

        if (!isNumeric(lengthInput)) {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", lengthInput);
            return;
        }
        length = Integer.parseInt(lengthInput);

        System.out.println("Input the number of possible symbols in the code:");
        String symbolsInput = sc.nextLine();
        int symbols;

        if (!isNumeric(symbolsInput)) {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", symbolsInput);
            return;
        }
        symbols = Integer.parseInt(symbolsInput);

        // --- validations ---
        if (length <= 0 || length > 36) {
            System.out.printf("Error: can't generate a secret number with a length of %d.%n", length);
            return;
        }
        if (symbols < length) {
            System.out.printf(
                    "Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n",
                    length, symbols
            );
            return;
        }
        if (symbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        // prepare & reveal range
        String mask = "*".repeat(length);
        System.out.printf("The secret is prepared: %s (%s).%n", mask, rangeDescriptor(symbols));

        // generate secret (0-9 then a-z, allow '0' first now)
        String secret = generateSecretCode(length, symbols);

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = sc.nextLine();

            if (guess.length() != length) {
                System.out.println("Please enter exactly " + length + " characters.");
                continue;
            }

            int bulls = 0, cows = 0;

            for (int i = 0; i < length; i++) {
                char s = secret.charAt(i);
                char g = guess.charAt(i);
                if (s == g) {
                    bulls++;
                } else if (secret.indexOf(g) != -1) {
                    cows++;
                }
            }

            if (bulls == 0 && cows == 0) {
                System.out.println("Grade: None");
            } else if (bulls > 0 && cows > 0) {
                System.out.println("Grade: " + bulls + " bull(s) and " + cows + " cow(s)");
            } else if (bulls > 0) {
                System.out.println("Grade: " + bulls + " bull(s)");
            } else {
                System.out.println("Grade: " + cows + " cow(s)");
            }

            if (bulls == length) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            turn++;
        }
    }

    // helper to check numeric input
    private static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String rangeDescriptor(int symbols) {
        if (symbols <= 10) {
            char last = (char) ('0' + (symbols - 1));
            return "0-" + last;
        } else {
            int letters = symbols - 10;
            char lastLetter = (char) ('a' + letters - 1);
            return "0-9, a-" + lastLetter;
        }
    }

    private static String generateSecretCode(int length, int symbols) {
        List<Character> pool = new ArrayList<>(symbols);

        for (char c = '0'; c <= '9' && pool.size() < symbols; c++) {
            pool.add(c);
        }
        for (char c = 'a'; pool.size() < symbols; c++) {
            pool.add(c);
        }

        Collections.shuffle(pool, new Random());

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(pool.get(i));
        }
        return sb.toString();
    }
}
