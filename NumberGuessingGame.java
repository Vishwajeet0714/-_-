import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int numberOfAttempts = 5;

        int score = 0;
        int rounds = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (true) {
            rounds++;
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            System.out.println("\nRound " + rounds + ":");
            System.out.println("Guess the number between " + lowerBound + " and " + upperBound + ".");
            System.out.println("You have " + numberOfAttempts + " attempts.");

            for (int attempt = 1; attempt <= numberOfAttempts; attempt++) {
                System.out.print("Attempt " + attempt + ": Enter your guess: ");
                int guess = scanner.nextInt();

                if (guess == targetNumber) {
                    System.out.println("Congratulations! You guessed the number!");
                    score += (numberOfAttempts - attempt + 1) * 10;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Your guess is too low. Try again.");
                } else {
                    System.out.println("Your guess is too high. Try again.");
                }
            }

            System.out.print("Do you want to play another round? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                System.out.println("Thank you for playing!");
                System.out.println("Your total score: " + score);
                break;
            }
        }
    }
}
