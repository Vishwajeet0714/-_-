import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//admin username: admin
//admin password: admin123

public class DigitalLibraryManagement {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> books = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();
    private static Member loggedInMember;

    public static void main(String[] args) {
        System.out.println("Welcome to the Digital Library!");

        // For simplicity, we add some sample books and members
        books.add(new Book(1, "Book 1", "Author 1", "Category 1"));
        books.add(new Book(2, "Book 2", "Author 2", "Category 2"));
        members.add(new Member(1001, "Member 1", "Contact 1"));
        members.add(new Member(1002, "Member 2", "Contact 2"));

        while (true) {
            printMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println("Thank you for using the Digital Library. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Quit");
        System.out.print("Enter your choice: ");
    }

    private static void adminLogin() {
        System.out.print("\nEnter admin username: ");
        String adminUsername = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String adminPassword = scanner.nextLine();

        // For simplicity, we use a predefined admin username and password
        if (adminUsername.equals("admin") && adminPassword.equals("admin123")) {
            System.out.println("Admin login successful. Welcome, Admin!");

            while (true) {
                printAdminMenu();
                int adminChoice = Integer.parseInt(scanner.nextLine());

                switch (adminChoice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        addMember();
                        break;
                    case 3:
                        viewBooks();
                        break;
                    case 4:
                        viewMembers();
                        break;
                    case 5:
                        System.out.println("Admin logout. Goodbye, Admin!");
                        loggedInMember = null;
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Admin login failed. Please try again.");
        }
    }

    private static void userLogin() {
        System.out.print("\nEnter member ID: ");
        int memberID = Integer.parseInt(scanner.nextLine());

        loggedInMember = findMember(memberID);
        if (loggedInMember != null) {
            System.out.println("User login successful. Welcome, " + loggedInMember.getName() + "!");
            while (true) {
                printUserMenu();
                int userChoice = Integer.parseInt(scanner.nextLine());

                switch (userChoice) {
                    case 1:
                        viewBooks();
                        break;
                    case 2:
                        borrowBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        System.out.println("User logout. Goodbye!");
                        loggedInMember = null;
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }
        } else {
            System.out.println("Member not found. Please try again.");
        }
    }

    private static Member findMember(int memberID) {
        for (Member member : members) {
            if (member.getMemberID() == memberID) {
                return member;
            }
        }
        return null;
    }

    private static void printAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. Add Book");
        System.out.println("2. Add Member");
        System.out.println("3. View Books");
        System.out.println("4. View Members");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.println("\nEnter book details:");
        System.out.print("Book ID: ");
        int bookID = Integer.parseInt(scanner.nextLine());
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();

        Book newBook = new Book(bookID, title, author, category);
        books.add(newBook);
        System.out.println("Book added successfully!");
    }

    private static void addMember() {
        System.out.println("\nEnter member details:");
        System.out.print("Member ID: ");
        int memberID = Integer.parseInt(scanner.nextLine());
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact Info: ");
        String contactInfo = scanner.nextLine();

        Member newMember = new Member(memberID, name, contactInfo);
        members.add(newMember);
        System.out.println("Member added successfully!");
    }

    private static void viewBooks() {
        System.out.println("\nList of Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void viewMembers() {
        System.out.println("\nList of Members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    private static void printUserMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. View Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void borrowBook() {
        // Implementation of borrowBook() method
        // (User-specific functionality goes here)
        // For simplicity, not implemented in this example.
    }

    private static void returnBook() {
        // Implementation of returnBook() method
        // (User-specific functionality goes here)
        // For simplicity, not implemented in this example.
    }
}

class Book {
    private int bookID;
    private String title;
    private String author;
    private String category;
    private boolean available;

    public Book(int bookID, String title, String author, String category) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    // ... Getters and setters ...

    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return "Book ID: " + bookID +
                ", Title: " + title +
                ", Author: " + author +
                ", Category: " + category +
                ", Available: " + (available ? "Yes" : "No");
    }
}

class Member {
    private int memberID;
    private String name;
    private String contactInfo;

    public Member(int memberID, String name, String contactInfo) {
        this.memberID = memberID;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // ... Getters and setters ...

    public String getName() {
        return name;
    }

    public int getMemberID() {
        return memberID;
    }

    @Override
    public String toString() {
        return "Member ID: " + memberID +
                ", Name: " + name +
                ", Contact Info: " + contactInfo;
    }
}
