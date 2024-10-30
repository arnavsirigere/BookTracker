import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PrintedBook extends Book {

    public static final double costPerPage = 10;

    private int totalPages;

    private static ArrayList<PrintedBook> lastThreeBooks = new ArrayList<PrintedBook>();

    public PrintedBook(String title, String author, String genre, int totalPages) {
        super(title, author, genre);
        this.totalPages = totalPages;
        calculateCost();
    }

    public void calculateCost() {
        double cost = totalPages * costPerPage;
        setCost(cost);
    }

    public double getCost() {
        ArrayList<Book> bookList = Book.getBookList();
        double totalCost = 0.0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) {
                totalCost += book.getBookCost();
            }
        }

        return totalCost;
    }

    public static double getAveragePages() {
        ArrayList<Book> bookList = Book.getBookList();

        int totalPrintedBooks = 0;
        int totalPages = 0;

        for (Book book : bookList) {
            if (book instanceof PrintedBook) {
                totalPrintedBooks++;
                totalPages += ((PrintedBook) book).totalPages;
            }
        }

        return (double) totalPages / totalPrintedBooks;
    }

    public static void displayLastThreeBooks() {

    }

    public int getNumberOfBooks() {
        // Returns number of printed books
        return 0;
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

            printWriter.print(String.format("\n%s,%s,Printed,%s", getTitle(), getAuthor(), totalPages));

            printWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
