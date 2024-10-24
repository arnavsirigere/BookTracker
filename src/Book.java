import java.util.ArrayList;

public abstract class Book implements BookInterface {
    protected String title;
    protected String author;
    protected String genre;
    protected double cost;

    protected static ArrayList<Book> bookList = new ArrayList<>();

    public Book() {

    }



    public double getTotalCost() {
        return 0.0;
    }

    public abstract double getCost();


    public abstract void storeBook();

    public static ArrayList<Book> getBookList() {
        return bookList;
    }


}
