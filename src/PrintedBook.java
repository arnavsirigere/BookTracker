import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PrintedBook extends Book {

    public static final double costPerPage = 10; // Encapsulation: Constant for cost per page, specific to PrintedBook

    private int totalPages; // Encapsulation: Private field for storing the total number of pages
    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<>(); // Encapsulation: List to manage recent books

    // Constructor to initialize a printed book's details
    public PrintedBook(String title, String author, String genre, int totalPages) {
        super(title, author, genre); // Inheritance: Reusing constructor of the parent class Book
        this.totalPages = totalPages;
        calculateCost(); // Abstraction: Implementation of an abstract method defined in Book
    }

    // Abstraction: Method to calculate cost specific to PrintedBook
    public void calculateCost() {
        double cost = totalPages * costPerPage;
        setCost(cost); // Encapsulation: Using setter from parent class to update the cost
    }

    // Polymorphism: Overriding method to calculate total cost of printed books
    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the shared book list
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) { // Check the type to ensure it is a PrintedBook
                totalCost += book.getBookCost(); // Use parent's method to get book cost
            }
        }

        return totalCost;
    }

    // Polymorphism: Method to calculate the average number of pages across all PrintedBook objects
    public static double getAveragePages() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the shared book list

        int totalPrintedBooks = 0;
        int totalPages = 0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) { // Check the type to ensure it is a PrintedBook
                totalPrintedBooks++;
                totalPages += ((PrintedBook) book).totalPages; // Access private field of PrintedBook
            }
        }

        return (double) totalPages / totalPrintedBooks; // Calculate and return the average
    }

    // Encapsulation: Method to display the last three printed books
    public static void displayLastThreeBooks() {
        for (int i = 0; i < lastThreeBooks.size(); i++) {
            System.out.println(lastThreeBooks.get(i).toString(i + 1)); // Use toString to format book details
        }
    }

    // Polymorphism: Overriding method to count the number of PrintedBook objects
    public int getNumberOfBooks() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the shared book list

        int printedBookCount = 0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) { // Check the type to ensure it is a PrintedBook
                printedBookCount++;
            }
        }

        return printedBookCount;
    }

    // Encapsulation: Store book details in memory and file
    public void storeBook() {
        try {
            if (lastThreeBooks.size() == 3) {
                lastThreeBooks.remove(0); // Remove the oldest book if the list already has three books
            }

            lastThreeBooks.add(this); // Add the current book to the recent books list
            Book.getBookList().add(this); // Add the current book to the shared book list

            // Write book details to the "booklist.txt" file
            FileWriter fileWriter = new FileWriter("booklist.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(String.format("\n%s,%s,Printed,%s", getTitle(), getAuthor(), totalPages));
            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage()); // Handle exceptions gracefully
        }
    }

    // Polymorphism: Overriding toString to provide formatted details specific to PrintedBook
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

    // Encapsulation: Getter for the list of last three printed books
    public static ArrayList<PrintedBook> getLastThreeBooks() {
        return lastThreeBooks;
    }
}
