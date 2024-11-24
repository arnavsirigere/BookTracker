import java.util.ArrayList;

public interface BookInterface {

    // Abstraction: Enforces implementing classes to provide the method for getting the number of books
    int getNumberOfBooks();

    // Abstraction: Enforces implementing classes to calculate and return the total cost of books
    double getTotalCost();

    // Polymorphism: Default method provides a generic implementation for displaying the last six books
    default void displayLastSixBooks() {
        ArrayList<Book> bookList = Book.getBookList(); // Retrieve the global book list (shared resource)
        
        // Loop through the last six books in the list
        for (int i = bookList.size() - 6; i < bookList.size(); i++) {
            if (i < 0) continue; // Handle cases where there are fewer than six books
            
            Book book = bookList.get(i); // Polymorphic reference to a Book instance
            
            // Polymorphism: Dynamically determine and call the appropriate toString method
            if (book instanceof PrintedBook) {
                System.out.println(((PrintedBook) book).toString(i + 1)); // Call PrintedBook's toString
            } else {
                System.out.println(((AudioBook) book).toString(i + 1)); // Call AudioBook's toString
            }
        }
    }
}
