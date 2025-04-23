import java.util.ArrayList;
import java.util.Scanner;

// Book class to represent a book in the library
class Book {
    private String title;
    private String author;
    private int quantity;

    // Constructor
    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to add books (increase quantity)
    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    // Method to check if a book has the same title and author
    public boolean equals(Book other) {
        return this.title.equalsIgnoreCase(other.getTitle()) &&
               this.author.equalsIgnoreCase(other.getAuthor());
    }

    // Method to check if a book has the same title
    public boolean hasSameTitle(String title) {
        return this.title.equalsIgnoreCase(title);
    }
}

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> books = new ArrayList<>();
        boolean running = true;

        System.out.println("Welcome to the Library Management System");

        do {
            System.out.println("\nPlease select an option:");
            System.out.println("A. Add Books");
            System.out.println("B. Borrow Books");
            System.out.println("R. Return Books");
            System.out.println("X. Exit");
            System.out.print("Enter your choice (A/B/R/X): ");

            // Input validation for menu choice
            String input;
            char choice;

            do {
                input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Error: No input provided. Please try again:");
                }
            } while (input.isEmpty());

            choice = input.toUpperCase().charAt(0);

            switch (choice) {
                case 'A': // Add Books
                    // Get and validate book title
                    String title;
                    do {
                        System.out.print("Enter book title: ");
                        title = scanner.nextLine().trim();
                        if (title.isEmpty()) {
                            System.out.println("Error: Title cannot be empty.");
                        }
                    } while (title.isEmpty());

                    // Get and validate author name
                    String author;
                    do {
                        System.out.print("Enter author name: ");
                        author = scanner.nextLine().trim();
                        if (author.isEmpty()) {
                            System.out.println("Error: Author name cannot be empty.");
                        }
                    } while (author.isEmpty());

                    // Get and validate quantity
                    int quantity = 0;
                    boolean validQuantity = false;
                    do {
                        try {
                            System.out.print("Enter quantity: ");
                            quantity = Integer.parseInt(scanner.nextLine().trim());
                            if (quantity <= 0) {
                                System.out.println("Error: Quantity must be a positive number.");
                            } else {
                                validQuantity = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. Please enter a valid number for quantity.");
                        }
                    } while (!validQuantity);

                    // Create a new book object
                    Book newBook = new Book(title, author, quantity);

                    // Check if the book already exists
                    boolean bookExists = false;
                    for (Book book : books) {
                        if (book.equals(newBook)) {
                            // Update quantity if book exists
                            book.addQuantity(quantity);
                            System.out.println("Book quantity updated. Current quantity: " + book.getQuantity());
                            bookExists = true;
                            break;
                        }
                    }

                    // Add new book if it doesn't exist
                    if (!bookExists) {
                        books.add(newBook);
                        System.out.println("New book added to the library.");
                    }
                    break;

                case 'B': // Borrow Books
                    // Check if there are any books in the library
                    if (books.isEmpty()) {
                        System.out.println("Error: The library is empty. Please add books first.");
                        break;
                    }

                    // Get and validate book title
                    String borrowTitle;
                    do {
                        System.out.print("Enter book title: ");
                        borrowTitle = scanner.nextLine().trim();
                        if (borrowTitle.isEmpty()) {
                            System.out.println("Error: Title cannot be empty.");
                        }
                    } while (borrowTitle.isEmpty());

                    // Get and validate borrow quantity
                    int borrowQuantity = 0;
                    boolean validBorrowQuantity = false;
                    do {
                        try {
                            System.out.print("Enter number of books to borrow: ");
                            borrowQuantity = Integer.parseInt(scanner.nextLine().trim());
                            if (borrowQuantity <= 0) {
                                System.out.println("Error: Quantity must be a positive number.");
                            } else {
                                validBorrowQuantity = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. Please enter a valid number for quantity.");
                        }
                    } while (!validBorrowQuantity);

                    // Find book and check if enough copies are available
                    boolean bookFound = false;
                    for (Book book : books) {
                        if (book.hasSameTitle(borrowTitle)) {
                            bookFound = true;
                            if (book.getQuantity() >= borrowQuantity) {
                                book.setQuantity(book.getQuantity() - borrowQuantity);
                                System.out.println("Successfully borrowed " + borrowQuantity + " copy/copies of '" + borrowTitle + "'");
                                System.out.println("Remaining copies: " + book.getQuantity());
                            } else {
                                System.out.println("Error: Not enough copies available. Available copies: " + book.getQuantity());
                            }
                            break;
                        }
                    }

                    // If book not found
                    if (!bookFound) {
                        System.out.println("Error: Book '" + borrowTitle + "' not found in the library.");
                    }
                    break;

                case 'R': // Return Books
                    // Check if there are any books in the library
                    if (books.isEmpty()) {
                        System.out.println("Error: The library is empty. Please add books first.");
                        break;
                    }

                    // Get and validate return title
                    String returnTitle;
                    do {
                        System.out.print("Enter book title: ");
                        returnTitle = scanner.nextLine().trim();
                        if (returnTitle.isEmpty()) {
                            System.out.println("Error: Title cannot be empty.");
                        }
                    } while (returnTitle.isEmpty());

                    // Get and validate return quantity
                    int returnQuantity = 0;
                    boolean validReturnQuantity = false;
                    do {
                        try {
                            System.out.print("Enter number of books to return: ");
                            returnQuantity = Integer.parseInt(scanner.nextLine().trim());
                            if (returnQuantity <= 0) {
                                System.out.println("Error: Quantity must be a positive number.");
                            } else {
                                validReturnQuantity = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid input. Please enter a valid number for quantity.");
                        }
                    } while (!validReturnQuantity);

                    // Find book and update quantity
                    boolean returnBookFound = false;
                    for (Book book : books) {
                        if (book.hasSameTitle(returnTitle)) {
                            book.addQuantity(returnQuantity);
                            System.out.println("Successfully returned " + returnQuantity + " copy/copies of '" + returnTitle + "'");
                            System.out.println("Current available copies: " + book.getQuantity());
                            returnBookFound = true;
                            break;
                        }
                    }

                    // If book not found
                    if (!returnBookFound) {
                        System.out.println("Error: Book '" + returnTitle + "' is not recognized in our library system.");
                    }
                    break;

                case 'X': // Exit
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter A, B, R, or X.");
            }
        } while (running);

        scanner.close();
    }
}