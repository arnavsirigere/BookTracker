import java.util.ArrayList;

public interface BookInterface {

    int getNumberOfBooks();

    double getTotalCost();

    default void displayLastSixBooks() {
        ArrayList<Book> bookList = Book.getBookList();
        for (int i = bookList.size() - 6; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (book instanceof PrintedBook) {
                System.out.println(((PrintedBook) book).toString(i + 1));
            } else {
                System.out.println(((AudioBook) book).toString(i + 1));
            }
        }

    }
}
