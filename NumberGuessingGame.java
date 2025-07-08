import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 7;
        int rounds = 0;
        int score = 0;

        boolean playAgain = true;

        while (playAgain) {
            rounds++;
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int minGuess = minRange;
            int maxGuess = maxRange;

            System.out.println("\nRound " + rounds + ": I'm thinking of a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + maxAttempts + " attempts. Try using binary search (start with the middle)!");

            int attempts = 0;
            boolean roundWon = false;

            while (attempts < maxAttempts) {
                attempts++;
                System.out.print("Attempt " + attempts + " - Enter your guess (" + minGuess + " to " + maxGuess + "): ");
                int guess = scanner.nextInt();

                if (guess == randomNumber) {
                    System.out.println("🎉 Congratulations! You guessed the number " + randomNumber + " in " + attempts + " attempts.");
                    score += (maxAttempts - attempts + 1);
                    roundWon = true;
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low.");
                    minGuess = Math.max(minGuess, guess + 1);
                } else {
                    System.out.println("Too high.");
                    maxGuess = Math.min(maxGuess, guess - 1);
                }

                if (minGuess > maxGuess) {
                    // Player guessed outside bounds repeatedly
                    System.out.println("⚠️ Warning: You're not narrowing down the range properly.");
                    minGuess = minRange;
                    maxGuess = maxRange;
                }

                // Give binary search advice
                int suggestedGuess = (minGuess + maxGuess) / 2;
                System.out.println("💡 Hint: Try guessing around " + suggestedGuess + " next.");
            }

            if (!roundWon) {
                System.out.println("❌ You ran out of attempts. The number was: " + randomNumber);
            }

            System.out.print("\nPlay again? (yes/no): ");
            String playAgainResponse = scanner.next().toLowerCase();
            playAgain = playAgainResponse.equals("yes");
        }

        System.out.println("\n🎮 Game Over! Your total score is: " + score);
        scanner.close();
    }
}

