import java.util.ArrayList;

public interface BookInterface {

    // Method to get the number of books (to be implemented by classes implementing this interface)
    int getNumberOfBooks();

    // Method to calculate and get the total cost of books (to be implemented by classes implementing this interface)
    double getTotalCost();

    // Default method to display the last six books
    default void displayLastSixBooks() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list
        
        // Iterate through the last six books in the list
        for (int i = bookList.size() - 6; i < bookList.size(); i++) {
            if (i < 0) continue; // Skip if the list contains fewer than six books
            
            Book book = bookList.get(i); // Get the book at the current index
            
            // Check the book type and display appropriate details
            if (book instanceof PrintedBook) {
                System.out.println(((PrintedBook) book).toString(i + 1)); // Call toString from PrintedBook
            } else {
                System.out.println(((AudioBook) book).toString(i + 1)); // Call toString from AudioBook
            }
        }
    }
}
