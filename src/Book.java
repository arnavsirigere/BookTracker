import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Book implements BookInterface {
    // Encapsulation: Private fields ensure data hiding and controlled access
    private String title; 
    private String author;
    private String genre;
    private double cost;

    // Encapsulation: Protected static list to manage all book instances
    protected static ArrayList<Book> bookList = new ArrayList<Book>();

    // Constructor for Book: Demonstrates abstraction by defining shared attributes
    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    // Polymorphism: Implements the interface method for calculating total cost
    @Override
    public double getTotalCost() {
        double totalCost = 0.0;

        // Loop through all books and sum up their costs
        for (Book book : bookList) {
            totalCost += book.cost; // Accessing private field within the same class
        }

        return totalCost;
    }

    // Encapsulation: Setter method for cost, providing controlled access
    public void setCost(double cost) {
        this.cost = cost;
    }

    // Abstraction: Abstract methods to enforce subclass-specific behavior
    public abstract void calculateCost();
    public abstract double getCost();
    public abstract void storeBook();

    // Encapsulation: Getter for cost to provide read access
    public double getBookCost() {
        return cost;
    }

    // Encapsulation: Getter for title
    public String getTitle() {
        return title;
    }

    // Encapsulation: Getter for author
    public String getAuthor() {
        return author;
    }

    // Encapsulation: Static method to access the book list
    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    // Polymorphism: Dynamically creates PrintedBook or AudioBook objects based on genre
    public static void readBookList() {
        try {
            File file = new File("booklist.txt"); // File containing book data
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String[] bookData = fileScanner.nextLine().split(",");
                String title = bookData[0];
                String author = bookData[1];
                String genre = bookData[2];

                Book book; // Polymorphic reference

                // Determine book type and instantiate the appropriate subclass
                if (genre.equals("Printed")) {
                    int totalPages = Integer.parseInt(bookData[3]);
                    book = new PrintedBook(title, author, genre, totalPages); // Polymorphism
                    PrintedBook.getLastThreeBooks().add((PrintedBook) book); // Managing subclass-specific list
                } else {
                    double totalLength = Double.parseDouble(bookData[3]);
                    book = new AudioBook(title, author, genre, totalLength); // Polymorphism
                    AudioBook.getLastThreeBooks().add((AudioBook) book); // Managing subclass-specific list
                }

                bookList.add(book); // Adding the book to the shared list
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage()); // Handling exceptions
        }
    }
}
