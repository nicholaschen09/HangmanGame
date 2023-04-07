import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        final int MAX_GUESSES = 7;
        final int SCREEN_HEIGHT = 10;
        Scanner sc = new Scanner(System.in);
        int guessesLeft = MAX_GUESSES;

        System.out.println("Enter a mystery word");
        String mysteryWord = sc.nextLine();
        System.out.println(mysteryWord);
        mysteryWord = mysteryWord.toLowerCase();
        printEmptyLines(SCREEN_HEIGHT);

        String guessedWord = initializeGuessedWord(mysteryWord);
        String rightGuesses = "";
        String wrongGuesses = "";

        while (guessesLeft > 0 && !guessedWord.equals(mysteryWord)) {
            printStatus(guessedWord, wrongGuesses, guessesLeft);
            System.out.println("Enter a letter");
            String input = sc.nextLine();
            printEmptyLines(SCREEN_HEIGHT);
            char guess = getGuess(input, wrongGuesses, rightGuesses);
            if (guess == '!') {
                System.out.println("Enter only a single character");
            } else if (guess == '?') {
                System.out.println("Enter a letter from the alphabet");
            } else if (guess == '.') {
                System.out.println("Enter a letter you haven't previously guessed");
            } else if (!mysteryWord.contains(guess+"")) {
                System.out.println("The guess was incorrect");
                guessesLeft = guessesLeft-1;
                wrongGuesses = wrongGuesses + guess;
            } else {
                guessedWord = fillGuessedWord(guessedWord, mysteryWord, guess);
                rightGuesses = rightGuesses + guess;
            }
        }

        printEmptyLines(SCREEN_HEIGHT);
        if (guessesLeft == 0) {
            System.out.println("Player 2 lost");
            System.out.println("The word was " + mysteryWord);
        } else {
            System.out.println("Player 2 won");
        }
        sc.close();
    }

    public static void printEmptyLines(int n) {
        for (int i=0; i<n; i++) {
            System.out.println();
        }
    }

    public static boolean isValidLetter(char c) {
        return c >= 'a' && c <= 'z';
    }

    public static String initializeGuessedWord(String word) {
        String newWord = "";
        for (int i=0; i<word.length(); i++) {
            if (isValidLetter(word.charAt(i))) {
                newWord = newWord + "-";
            } else {
                newWord = newWord + word.charAt(i);
            }
        }
        return newWord;
    }

    public static char getGuess(String input, String wrongGuesses, String rightGuesses) {
        if (input.length() != 1) {
            return '!';
        }
        if (wrongGuesses.contains(input) || rightGuesses.contains(input)) {
            return '.';
        }
        if (!isValidLetter(input.charAt(0))) {
            return '?';
        }
        return input.charAt(0);
    }

    public static String fillGuessedWord(String guessedWord, String mysteryWord, char guess) {
        String newGuessedWord = "";
        for (int i=0; i<guessedWord.length(); i++) {
            if (guess == mysteryWord.charAt(i)) {
                newGuessedWord = newGuessedWord + guess;
            } else {
                newGuessedWord = newGuessedWord + guessedWord.charAt(i);
            }
        }
        return newGuessedWord;
    }

    public static void printStatus(String guessedWord, String guessedLetters, int guessesLeft) {
        System.out.println("You have " + guessesLeft + " guesses left");
        if (!guessedLetters.equals("")) {
            System.out.println("Incorrect letters: " + guessedLetters);
        }
        System.out.println("The word so far is " + guessedWord);
    }
}

