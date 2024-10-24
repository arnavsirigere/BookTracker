import java.util.ArrayList;

public class AudioBook extends Book {

    private int totalLength;

    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<PrintedBook>();

    public AudioBook() {

    }



    public double getCost() {
        return 0.0;
    }

    public static double getAverageLength() {
        return 0.0;
    }

    public static void displayLastThreeBooks() {

    }

    public int getNumberOfBooks() {
        // Returns number of printed books
        return 0;
    }

    public double getTotalCost() {
        // Computes average costs of all books
        return 0.0;
    }

    public void storeBook() {
        // Adds book to lastThreeBooks arraylist
        // Adds book to .txt file
    }


}
