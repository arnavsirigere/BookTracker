import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PrintedBook extends Book {

    public static final double costPerPage = 10; // Cost per page for a printed book

    private int totalPages; // Total number of pages in the printed book

    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<>(); // Stores the last three printed books added

    // Constructor to initialize a printed book's details
    public PrintedBook(String title, String author, String genre, int totalPages) {
        super(title, author, genre); // Call the parent class constructor
        this.totalPages = totalPages;
        calculateCost(); // Calculate and set the cost of the book
    }

    // Calculate the cost of the printed book based on the total pages and cost per page
    public void calculateCost() {
        double cost = totalPages * costPerPage;
        setCost(cost); // Set the calculated cost in the parent class
    }

    // Get the total cost of all printed books from the book list
    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) {
                totalCost += book.getBookCost(); // Sum up the cost of all printed books
            }
        }

        return totalCost;
    }

    // Calculate the average number of pages in all printed books
    public static double getAveragePages() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list

        int totalPrintedBooks = 0;
        int totalPages = 0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) {
                totalPrintedBooks++;
                totalPages += ((PrintedBook) book).totalPages; // Sum up pages of all printed books
            }
        }

        return (double) totalPages / totalPrintedBooks; // Return the average number of pages
    }

    // Display the details of the last three printed books added
    public static void displayLastThreeBooks() {
        for (int i = 0; i < lastThreeBooks.size(); i++) {
            System.out.println(lastThreeBooks.get(i).toString(i + 1));
        }
    }

    // Get the total number of printed books in the book list
    public int getNumberOfBooks() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list

        int printedBookCount = 0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) {
                printedBookCount++;
            }
        }

        return printedBookCount;
    }

    // Store the printed book in the system (memory and file)
    public void storeBook() {
        try {
            if (lastThreeBooks.size() == 3) {
                lastThreeBooks.remove(0); // Remove the oldest book if the list already has three books
            }

            lastThreeBooks.add(this); // Add the current book to the list of recent printed books
            Book.getBookList().add(this); // Add the current book to the global book list

            // Append the book details to the file "booklist.txt"
            FileWriter fileWriter = new FileWriter("booklist.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(String.format("\n%s,%s,Printed,%s", getTitle(), getAuthor(), totalPages));
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle exceptions and print error messages
        }
    }

    // Generate a formatted string representing the printed book's details
    public String toString(int num) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(); // Format currency
        String formattedAmount = currencyFormatter.format(getBookCost()); // Format book cost

        return String.format(
                "Book %d:\n" +
                        "  Title: %s\n" +
                        "  Author: %s\n" +
                        "  Genre: Printed 📚\n" +
                        "  Pages: %d\n" +
                        "  Cost: %s\n",
                num, getTitle(), getAuthor(), totalPages, formattedAmount
        );
    }

    // Getter for the list of last three printed books
    public static ArrayList<PrintedBook> getLastThreeBooks() {
        return lastThreeBooks;
    }
}
