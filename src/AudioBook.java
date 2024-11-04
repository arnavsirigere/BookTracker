import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class AudioBook extends Book {

    public static final double costPerMinute = PrintedBook.costPerPage / 2;

    private double totalLength;

    private static ArrayList<AudioBook> lastThreeBooks = new ArrayList<AudioBook>();

    public AudioBook(String title, String author, String genre, double totalLength) {
        super(title, author, genre);
        this.totalLength = totalLength;
        calculateCost();
    }

    public void calculateCost() {
        double cost = totalLength * costPerMinute;
        setCost(cost);
    }

    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList();
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof AudioBook) {
                totalCost += book.getBookCost();
            }
        }

        return totalCost;
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
        for (int i = 0; i < lastThreeBooks.size(); i++) {
            System.out.println(lastThreeBooks.get(i).toString(i + 1));
        }
    }

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

    public void storeBook() {
        try {
            if (lastThreeBooks.size() == 3) {
                lastThreeBooks.removeFirst();
            }

            lastThreeBooks.add(this);
            Book.getBookList().add(this);

            FileWriter fileWriter = new FileWriter("booklist.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(String.format("\n%s,%s,Audio,%s", getTitle(), getAuthor(), totalLength));

            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String toString(int num) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String formattedAmount = currencyFormatter.format(getBookCost());

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

    public static ArrayList<AudioBook> getLastThreeBooks() {
        return lastThreeBooks;
    }
}
