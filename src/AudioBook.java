import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AudioBook extends Book {

    // Cost per minute of audio book, derived from half the cost per page of a printed book
    public static final double costPerMinute = PrintedBook.costPerPage / 2;

    private double totalLength; // Total length of the audio book in minutes
    private static ArrayList<AudioBook> lastThreeBooks = new ArrayList<>(); // Stores the last three audio books added

    // Constructor to initialize audio book details
    public AudioBook(String title, String author, String genre, double totalLength) {
        super(title, author, genre); // Call the parent class constructor
        this.totalLength = totalLength;
        calculateCost(); // Calculate and set the cost of the book
    }

    // Calculate the cost of the audio book based on its length and cost per minute
    public void calculateCost() {
        double cost = totalLength * costPerMinute;
        setCost(cost); // Set the calculated cost in the parent class
    }

    // Get the total cost of all audio books from the book list
    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) {
                totalCost += book.getBookCost(); // Sum up the cost of all audio books
            }
        }

        return totalCost;
    }

    // Calculate the average length of all audio books
    public static double getAverageLength() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list

        int totalAudioBooks = 0;
        double totalLength = 0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) {
                totalAudioBooks++;
                totalLength += ((AudioBook) book).totalLength; // Sum up lengths of all audio books
            }
        }

        return totalLength / totalAudioBooks; // Return the average length
    }

    // Display the details of the last three audio books added
    public static void displayLastThreeBooks() {
        for (int i = 0; i < lastThreeBooks.size(); i++) {
            System.out.println(lastThreeBooks.get(i).toString(i + 1));
        }
    }

    // Get the total number of audio books in the book list
    public int getNumberOfBooks() {
        ArrayList<Book> bookList = Book.getBookList();

        int audioBookCount = 0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) {
                audioBookCount++;
            }
        }

        return audioBookCount;
    }

    // Store the audio book in the system (memory and file)
    public void storeBook() {
        try {
            if (lastThreeBooks.size() == 3) {
                lastThreeBooks.remove(0); // Remove the oldest book if the list already has three books
            }

            lastThreeBooks.add(this); // Add the current book to the list of recent audio books
            Book.getBookList().add(this); // Add the current book to the global book list

            // Append the book details to the file "booklist.txt"
            FileWriter fileWriter = new FileWriter("booklist.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(String.format("\n%s,%s,Audio,%s", getTitle(), getAuthor(), totalLength));
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle exceptions and print error messages
        }
    }

    // Generate a formatted string representing the audio book's details
    public String toString(int num) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(); // Format currency
        String formattedAmount = currencyFormatter.format(getBookCost()); // Format book cost

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

    // Getter for the list of last three audio books
    public static ArrayList<AudioBook> getLastThreeBooks() {
        return lastThreeBooks;
    }
}
