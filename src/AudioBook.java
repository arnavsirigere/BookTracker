import java.util.ArrayList;

public class AudioBook extends Book {

    private double totalLength;

    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<PrintedBook>();

    public AudioBook(String title, String author, String genre, double cost, double totalLength) {
        super(title, author, genre, cost);
        this.totalLength = totalLength;
    }

    public double getCost() {
        return 0.0;
    }

    public static double getAverageLength() {
        ArrayList<Book> bookList = Book.getBookList();

        int totalAudioBooks = 0;
        double totalLength = 0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) {
                totalAudioBooks++;
                totalLength += ((AudioBook) book).totalLength;
            }
        }

        return totalLength / totalAudioBooks;
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
