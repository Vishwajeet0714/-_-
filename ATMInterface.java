import java.util.Scanner;
import java.util.ArrayList;
//Enter user id: user123
//Enter user pin: 1234
public class ATMInterface {
    private static Scanner scanner = new Scanner(System.in);
    private static String userId = "user123"; // Replace with real user id
    private static String userPin = "1234"; // Replace with real user pin
    private static double balance = 1000.0; // Replace with real initial account balance
    private static ArrayList<String> transactionsHistory = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the ATM!");

        // Authenticate user
        if (login()) {
            System.out.println("Login successful. Welcome, " + userId);

            while (true) {
                printMenu();
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        viewBalance();
                        break;
                    case 2:
                        withdraw();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        transfer();
                        break;
                    case 5:
                        transactionsHistory();
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Login failed. Invalid user id or pin. Exiting...");
            System.exit(1);
        }
    }

    private static boolean login() {
        System.out.print("Enter user id: ");
        String inputId = scanner.nextLine();
        System.out.print("Enter user pin: ");
        String inputPin = scanner.nextLine();

        return inputId.equals(userId) && inputPin.equals(userPin);
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. View Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Transactions History");
        System.out.println("6. Quit");
    }

    private static void viewBalance() {
        System.out.println("Your current balance: $" + balance);
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. Your new balance: $" + balance);
            transactionsHistory.add("Withdraw: -$" + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance. Please try again.");
        }
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. Your new balance: $" + balance);
            transactionsHistory.add("Deposit: +$" + amount);
        } else {
            System.out.println("Invalid amount. Please try again.");
        }
    }

    private static void transfer() {
        System.out.print("Enter the recipient's user id: ");
        String recipientId = scanner.nextLine();
        System.out.print("Enter the amount to transfer: $");
        double amount = Double.parseDouble(scanner.nextLine());

        if (amount > 0 && amount <= balance) {
            // In a real-world application, you would implement the transfer logic here
            // For demonstration purposes, let's assume the transfer is successful
            balance -= amount;
            System.out.println("Transfer successful. Your new balance: $" + balance);
            transactionsHistory.add("Transfer to " + recipientId + ": -$" + amount);
        } else {
            System.out.println("Invalid amount or insufficient balance. Please try again.");
        }
    }

    private static void transactionsHistory() {
        System.out.println("\nTransaction History:");
        for (String transaction : transactionsHistory) {
            System.out.println(transaction);
        }
    }
}
