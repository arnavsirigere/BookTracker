import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AudioBook extends Book {

    // Encapsulation: Constant field for cost calculation, specific to AudioBook
    public static final double costPerMinute = PrintedBook.costPerPage / 2;

    private double totalLength; // Encapsulation: Private field accessed via methods
    private static ArrayList<AudioBook> lastThreeBooks = new ArrayList<>(); // Encapsulation of recent audio books

    // Constructor to initialize audio book details
    public AudioBook(String title, String author, String genre, double totalLength) {
        super(title, author, genre); // Inheritance: Calling the parent class constructor
        this.totalLength = totalLength;
        calculateCost(); // Abstraction: Using an abstract method defined in Book
    }

    // Abstraction: Implementation of abstract method to calculate cost specific to AudioBook
    public void calculateCost() {
        double cost = totalLength * costPerMinute;
        setCost(cost); // Encapsulation: Setting the calculated cost via setter
    }

    // Polymorphism: Overriding and customizing behavior for AudioBook
    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the shared book list
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) { // Polymorphic check for specific type
                totalCost += book.getBookCost(); // Use parent's method to get cost
            }
        }

        return totalCost;
    }

    // Polymorphism: Method to calculate average length specific to AudioBook
    public static double getAverageLength() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the shared book list

        int totalAudioBooks = 0;
        double totalLength = 0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) { // Polymorphic behavior based on type
                totalAudioBooks++;
                totalLength += ((AudioBook) book).totalLength; // Access specific field
            }
        }

        return totalLength / totalAudioBooks; // Calculate and return the average
    }

    // Encapsulation: Displaying last three audio books from a private list
    public static void displayLastThreeBooks() {
        for (int i = 0; i < lastThreeBooks.size(); i++) {
            System.out.println(lastThreeBooks.get(i).toString(i + 1)); // Display formatted details
        }
    }

    // Polymorphism: Method to count audio books using the shared book list
    public int getNumberOfBooks() {
        ArrayList<Book> bookList = Book.getBookList();

        int audioBookCount = 0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) { // Check specific book type
                audioBookCount++;
            }
        }

        return audioBookCount;
    }

    // Encapsulation: Method to store book data in memory and file
    public void storeBook() {
        try {
            if (lastThreeBooks.size() == 3) {
                lastThreeBooks.remove(0); // Encapsulation: Manage recent book list
            }

            lastThreeBooks.add(this); // Add current book to recent list
            Book.getBookList().add(this); // Add to shared book list

            // File operations encapsulated to handle persistence
            FileWriter fileWriter = new FileWriter("booklist.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(String.format("\n%s,%s,Audio,%s", getTitle(), getAuthor(), totalLength));
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle exceptions
        }
    }

    // Polymorphism: Custom string representation for AudioBook
    public String toString(int num) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(); // Format currency
        String formattedAmount = currencyFormatter.format(getBookCost()); // Format cost

        return String.format(
                "Book %d:\n" +
                        "  Title: %s\n" +
                        "  Author: %s\n" +
                        "  Genre: Audio 🎧\n" +
                        "  Length: %.2f\n" +
                        "  Cost: %s\n",
                num, getTitle(), getAuthor(), totalLength, formattedAmount
        );
    }

    // Encapsulation: Getter for the list of last three audio books
    public static ArrayList<AudioBook> getLastThreeBooks() {
        return lastThreeBooks;
    }
}
