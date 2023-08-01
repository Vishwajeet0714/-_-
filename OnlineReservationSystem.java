import java.util.*;
//Enter login id: user1
//Enter password: password1

public class OnlineReservationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();

    public static void main(String[] args) {
        // Sample data - You would typically use a database for this
        users.put("user1", "password1");
        users.put("user2", "password2");

        boolean loggedIn = false;
        String loggedInUser = "";

        while (!loggedIn) {
            System.out.print("Enter login id: ");
            String loginId = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (isValidUser(loginId, password)) {
                loggedInUser = loginId;
                loggedIn = true;
            } else {
                System.out.println("Invalid login credentials. Try again.");
            }
        }

        System.out.println("Login successful. Welcome, " + loggedInUser);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Reservation System");
            System.out.println("2. Cancellation Form");
            System.out.println("3. Exit");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    reservationSystem(loggedInUser);
                    break;
                case 2:
                    cancellationForm(loggedInUser);
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Reservation System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // Login Form - Check if the user is valid
    private static boolean isValidUser(String loginId, String password) {
        return users.containsKey(loginId) && users.get(loginId).equals(password);
    }

    // Reservation System
    private static void reservationSystem(String loggedInUser) {
        System.out.println("Welcome to the Reservation System, " + loggedInUser);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey: ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter source (from) station: ");
        String sourceStation = scanner.nextLine();
        System.out.print("Enter destination (to) station: ");
        String destinationStation = scanner.nextLine();

        String pnr = generatePNR();
        Reservation reservation = new Reservation(pnr, name, trainNumber, classType, dateOfJourney, sourceStation, destinationStation);
        reservations.put(pnr, reservation);

        System.out.println("Reservation successful. Your PNR is: " + pnr);
    }

    // Helper method to generate a random PNR number
    private static String generatePNR() {
        // For simplicity, this method generates a random PNR, but in a real-world scenario, it should be more sophisticated.
        return "PNR" + UUID.randomUUID().toString().replace("-", "").substring(0, 10).toUpperCase();
    }

    // Cancellation Form
    private static void cancellationForm(String loggedInUser) {
        System.out.println("Welcome to the Cancellation Form, " + loggedInUser);
        System.out.print("Enter your PNR number: ");
        String pnr = scanner.nextLine();

        if (reservations.containsKey(pnr)) {
            Reservation reservation = reservations.get(pnr);
            // Display reservation details
            System.out.println("Reservation details:");
            System.out.println("PNR: " + reservation.getPNR());
            System.out.println("Name: " + reservation.getName());
            System.out.println("Train Number: " + reservation.getTrainNumber());
            System.out.println("Class Type: " + reservation.getClassType());
            System.out.println("Date of Journey: " + reservation.getDateOfJourney());
            System.out.println("From (Place): " + reservation.getSourceStation());
            System.out.println("To (Destination): " + reservation.getDestinationStation());

            System.out.print("Press 'OK' to confirm cancellation (Type 'OK'): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("OK")) {
                reservations.remove(pnr);
                System.out.println("Reservation with PNR " + pnr + " has been canceled.");
            } else {
                System.out.println("Cancellation not confirmed. Reservation remains active.");
            }
        } else {
            System.out.println("Invalid PNR number. Please check and try again.");
        }
    }

    // Helper class for Reservation
    private static class Reservation {
        private String pnr;
        private String name;
        private String trainNumber;
        private String classType;
        private String dateOfJourney;
        private String sourceStation;
        private String destinationStation;

        public Reservation(String pnr, String name, String trainNumber, String classType, String dateOfJourney, String sourceStation, String destinationStation) {
            this.pnr = pnr;
            this.name = name;
            this.trainNumber = trainNumber;
            this.classType = classType;
            this.dateOfJourney = dateOfJourney;
            this.sourceStation = sourceStation;
            this.destinationStation = destinationStation;
        }

        // Getters for reservation details
        public String getPNR() {
            return pnr;
        }

        public String getName() {
            return name;
        }

        public String getTrainNumber() {
            return trainNumber;
        }

        public String getClassType() {
            return classType;
        }

        public String getDateOfJourney() {
            return dateOfJourney;
        }

        public String getSourceStation() {
            return sourceStation;
        }

        public String getDestinationStation() {
            return destinationStation;
        }
    }
}
