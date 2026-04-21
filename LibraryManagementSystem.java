import java.util.ArrayList;
import java.util.Scanner;

// --- BOOK CLASS ---
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true; // Books are available by default when added
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed";
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Status: " + status;
    }
}

// --- LIBRARY CLASS ---
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Success: '" + book.getTitle() + "' added to the library.");
    }

    public void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The library is currently empty.");
            return;
        }
        System.out.println("\n--- Library Catalog ---");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    public void borrowBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (book.isAvailable()) {
                    book.setAvailable(false);
                    System.out.println("Success: You have borrowed '" + book.getTitle() + "'.");
                } else {
                    System.out.println("Sorry: '" + book.getTitle() + "' is currently checked out.");
                }
                return;
            }
        }
        System.out.println("Error: Book with ID " + id + " not found.");
    }

    public void returnBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                if (!book.isAvailable()) {
                    book.setAvailable(true);
                    System.out.println("Success: You have returned '" + book.getTitle() + "'.");
                } else {
                    System.out.println("Notice: '" + book.getTitle() + "' was not borrowed.");
                }
                return;
            }
        }
        System.out.println("Error: Book with ID " + id + " not found.");
    }
}

// --- MAIN CLASS ---
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // Pre-loading some books for testing
        library.addBook(new Book(101, "Clean Code", "Robert C. Martin"));
        library.addBook(new Book(102, "Effective Java", "Joshua Bloch"));

        System.out.println("\nWelcome to the Library Management System!");

        while (!exit) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View all books");
            System.out.println("2. Add a new book");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    library.viewAllBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                    break;
                case 3:
                    System.out.print("Enter the ID of the book to borrow: ");
                    int borrowId = scanner.nextInt();
                    library.borrowBook(borrowId);
                    break;
                case 4:
                    System.out.print("Enter the ID of the book to return: ");
                    int returnId = scanner.nextInt();
                    library.returnBook(returnId);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
