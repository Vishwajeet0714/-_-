import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
//Enter username: user123
//Enter password: pass123
public class OnlineExaminationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static String username = "user123"; // Replace with real username
    private static String password = "pass123"; // Replace with real password
    private static int score = 0;
    private static boolean examTaken = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the Online Examination System!");

        if (login()) {
            System.out.println("Login successful. Welcome, " + username);

            updateProfile();

            Timer timer = new Timer();
            TimerTask autoSubmitTask = new TimerTask() {
                @Override
                public void run() {
                    if (!examTaken) {
                        System.out.println("Time's up! Automatically submitting the exam...");
                        submitExam();
                    }
                }
            };

            // Mock exam duration: 60 seconds
            long examDuration = 60 * 1000;
            timer.schedule(autoSubmitTask, examDuration);

            takeExam();

            timer.cancel(); // Cancel the timer task after the exam is completed

            System.out.println("Your final score: " + score);
            logout();
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    private static boolean login() {
        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    private static void updateProfile() {
        System.out.print("Do you want to update your profile? (yes/no): ");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.print("Enter your new username: ");
            username = scanner.nextLine();
            System.out.print("Enter your new password: ");
            password = scanner.nextLine();
            System.out.println("Profile updated successfully.");
        }
    }

    private static void takeExam() {
        System.out.println("Start the exam:");

        // Questions and options
        String[] questions = {
                "Q1. What is 2 + 2?",
                "Q2. Which planet is known as the 'Red Planet'?",
                "Q3. Who is the author of 'Romeo and Juliet'?"
        };
        String[][] options = {
                {"a) 3", "b) 4", "c) 5", "d) 6"},
                {"a) Venus", "b) Mars", "c) Jupiter", "d) Saturn"},
                {"a) William Shakespeare", "b) Charles Dickens", "c) Jane Austen", "d) Mark Twain"}
        };
        String[] correctAnswers = {"b", "b", "a"};

        for (int i = 0; i < questions.length; i++) {
            System.out.println(questions[i]);
            for (String option : options[i]) {
                System.out.println(option);
            }
            System.out.print("Your answer (a/b/c/d): ");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equalsIgnoreCase(correctAnswers[i])) {
                score++;
            }
        }

        examTaken = true;
        System.out.println("Exam completed.");
    }

    private static void submitExam() {
        System.out.println("Exam submitted successfully.");
    }

    private static void logout() {
        System.out.println("Closing session and logging out. Goodbye!");
    }
}
