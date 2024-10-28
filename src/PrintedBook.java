import java.util.ArrayList;

public class PrintedBook extends Book {

    private int totalPages;

    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<PrintedBook>();

    public PrintedBook(String title, String author, String genre, double cost, int totalPages) {
        super(title, author, genre, cost);
        this.totalPages = totalPages;

    }

    public double getCost() {
        return 0.0;
    }


    public static double getAveragePages() {
        return 0.0;
    }

    public static void displayLastThreeBooks() {

    }

    public int getNumberOfBooks() {
        // Returns number of printed books
        return 0;
    }

    public void storeBook() {
        // Adds book to lastThreeBooks arraylist
        // Adds book to .txt file
    }


}
