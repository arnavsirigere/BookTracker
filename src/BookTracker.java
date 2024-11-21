import java.util.Scanner;

public class BookTracker {

    private static final Scanner scanner = new Scanner(System.in); // Scanner for user input
    private static final int totalMenuOptions = 13; // Total number of menu options

    public static void main(String[] args) {
        // Initialize book list by reading from a file or other data source
        Book.readBookList();

        // Print the menu for user interaction
        printMenu();

        // Main loop for handling user inputs and menu options
        while (true) {
            // Get the user's selected menu option
            int option = getOption();

            // Handle the selected menu option
            switch(option) {
                case 1: { // Add a new printed book
                    System.out.print("Please enter the title of the book: ");
                    String title = scanner.nextLine();

                    System.out.print("Please enter the author of the book: ");
                    String author = scanner.nextLine();

                    int totalPages = getValidInteger("Please enter the total pages in the book: ");

                    // Create a new printed book and store it
                    PrintedBook newBook = new PrintedBook(title, author, "Printed", totalPages);
                    newBook.storeBook();
                    break;
                }

                case 2: { // Add a new audio book
                    System.out.print("Please enter the title of the book: ");
                    String title = scanner.nextLine();

                    System.out.print("Please enter the author of the book: ");
                    String author = scanner.nextLine();

                    double totalLength = getValidDouble("Please enter the total length of the audio book: ");

                    // Create a new audio book and store it
                    AudioBook newBook = new AudioBook(title, author, "Audio", totalLength);
                    newBook.storeBook();
                    break;
                }

                case 3: { // Display last six completed books
                    System.out.println("Here are the details of the six most recently completed books:\n");
                    Book.getBookList().getFirst().displayLastSixBooks();
                    break;
                }

                case 4: { // Display last three completed printed books
                    System.out.println("Here are the details of the three most recently completed printed books:\n");
                    PrintedBook.displayLastThreeBooks();
                    break;
                }

                case 5: { // Display last three completed audio books
                    System.out.println("Here are the details of the three most recently completed audio books:\n");
                    AudioBook.displayLastThreeBooks();
                    break;
                }

                case 6: { // Display total cost of all books
                    Book book = Book.getBookList().getFirst();
                    System.out.printf("The total cost of all books is $%.2f\n", book.getTotalCost());
                    break;
                }

                case 7: { // Display total cost of all printed books
                    PrintedBook printedBook = findPrintedBook();
                    System.out.printf("The total cost of all printed books is $%.2f\n", printedBook.getCost());
                    break;
                }

                case 8: { // Display total cost of all audio books
                    AudioBook audioBook = findAudioBook();
                    System.out.printf("The total cost of all audio books is $%.2f\n", audioBook.getCost());
                    break;
                }

                case 9: { // Display total number of printed books completed
                    PrintedBook printedBook = findPrintedBook();
                    int totalPrintedBooks = printedBook.getNumberOfBooks();
                    System.out.printf("You have completed %d printed book%s.\n", totalPrintedBooks, totalPrintedBooks == 1 ? "" : "s");
                    break;
                }

                case 10: { // Display total number of audio books completed
                    AudioBook audioBook = findAudioBook();
                    int totalAudioBooks = audioBook.getNumberOfBooks();
                    System.out.printf("You have completed %d audio book%s.\n", totalAudioBooks, totalAudioBooks == 1 ? "" : "s");
                    break;
                }

                case 11: { // Display average page count of printed books
                    System.out.printf("The average page count of all printed books is %.2f\n", PrintedBook.getAveragePages());
                    break;
                }

                case 12: { // Display average duration of audio books
                    System.out.printf("The average duration of all audio books is %.2f minutes.\n", AudioBook.getAverageLength());
                    break;
                }

                case 13: { // Exit the program
                    System.out.println("Exiting Menu . . .");
                    return;
                }
            }
            System.out.println();
        }
    }

    // Get a valid menu option from the user
    public static int getOption() {
        int option = -1;
        boolean invalidInputProvided = false;

        System.out.printf("Please select an action by entering a number (1 to %d): ", totalMenuOptions);

        while (option < 1 || option > totalMenuOptions) {
            if (invalidInputProvided) {
                System.out.printf("Please select an action by entering a number (1 to %d): ", totalMenuOptions);
            }

            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                if (option < 1 || option > totalMenuOptions) {
                    System.out.printf("Invalid input! Please enter a number between 1 and %d for your selection.\n", totalMenuOptions);
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number and try again.");
                scanner.next();
            }

            invalidInputProvided = true;
        }

        scanner.nextLine(); // Clear the input buffer
        return option;
    }

    // Get a valid positive integer from the user
    public static int getValidInteger(String prompt) {
        int positiveInt = -1;

        while (positiveInt <= 0) {
            System.out.print(prompt);

            if (scanner.hasNextInt()) {
                positiveInt = scanner.nextInt();
                if (positiveInt > 0) {
                    return positiveInt;
                }
            } else {
                scanner.next();
            }

            System.out.println("That is not a valid input. Please try again.");
        }

        return 0;
    }

    // Get a valid positive double from the user
    public static double getValidDouble(String prompt) {
        double positiveDouble = -1.0;

        while (positiveDouble <= 0) {
            System.out.print(prompt);

            if (scanner.hasNextDouble()) {
                positiveDouble = scanner.nextDouble();
                if (positiveDouble > 0) {
                    return positiveDouble;
                }
            } else {
                scanner.next();
            }

            System.out.println("That is not a valid input. Please try again.");
        }

        return 0;
    }

    // Find the first printed book in the list
    public static PrintedBook findPrintedBook() {
        for (Book book : Book.getBookList()) {
            if (book instanceof PrintedBook) {
                return (PrintedBook) book;
            }
        }
        return new PrintedBook("", "", "Printed", 0);
    }

    // Find the first audio book in the list
    public static AudioBook findAudioBook() {
        for (Book book : Book.getBookList()) {
            if (book instanceof AudioBook) {
                return (AudioBook) book;
            }
        }
        return new AudioBook("", "", "Audio", 0);
    }

    // Print the menu to the console
    public static void printMenu() {
        System.out.println("=================================================");
        System.out.println("                BOOK TRACKING APPLICATION        ");
        System.out.println("=================================================");
        System.out.println("Available options:");
        System.out.println("1. Add a New Printed Book");
        System.out.println("2. Add a New Audio Book");
        System.out.println("3. View Last Six Books");
        System.out.println("4. View Last Three Printed Books");
        System.out.println("5. View Last Three Audio Books");
        System.out.println("6. View Total Cost of all Books");
        System.out.println("7. View Total Cost of all Printed Books");
        System.out.println("8. View Total Cost of all Audio Books");
        System.out.println("9. View Total Printed Books Completed");
        System.out.println("10. View Total Audio Books Completed");
        System.out.println("11. View Average Page Count of all Printed Books");
        System.out.println("12. View Average Duration of all Audio Books");
        System.out.println("13. Exit");
        System.out.println("=================================================\n");
    }
}
