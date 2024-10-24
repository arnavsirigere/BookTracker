import java.util.ArrayList;

public interface BookInterface {

    int getNumberOfBooks();

    double getTotalCost();

    default void displayLast6Books() {
        ArrayList<Book> books = Book.getBookList(); // Assuming a method to get the static list of books

    }
}
