import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Book implements BookInterface {
    private String title;
    private String author;
    private String genre;
    private double cost;

    protected static ArrayList<Book> bookList = new ArrayList<Book>();

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    @Override
    public double getTotalCost() {
        double totalCost = 0.0;

        for (Book book : bookList) {
            totalCost += book.cost;
        }

        return totalCost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public abstract void calculateCost();

    public abstract double getCost();

    public abstract void storeBook();

    public double getBookCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void readBookList() {
        try {
            File file = new File("booklist.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String[] bookData = fileScanner.nextLine().split(",");
                String title = bookData[0];
                String author = bookData[1];
                String genre = bookData[2];

                Book book;

                if (genre.equals("Printed")) {
                    int totalPages = Integer.parseInt(bookData[3]);
                    // Polymorphism
                    book = new PrintedBook(title, author, genre, totalPages);
                    PrintedBook.getLastThreeBooks().add((PrintedBook) book);
                } else {
                    double totalLength = Double.parseDouble(bookData[3]);
                    // Polymorphism
                    book = new AudioBook(title, author, genre, totalLength);
                    AudioBook.getLastThreeBooks().add((AudioBook) book);
                }

                bookList.add(book);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
