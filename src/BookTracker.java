import java.util.Scanner;

public class BookTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int totalMenuOptions = 3;

    public static void main(String[] args) {
        Book.readBookList();

        printMenu();

        while (true) {

            int option = getOption();

            switch(option) {
                case 1: {
                    System.out.print("Please enter the title of the book: ");
                    String title = scanner.nextLine();

                    System.out.print("Please enter the author of the book: ");
                    String author = scanner.nextLine();

                    int totalPages = getValidInteger("Please enter the total pages in the book: ");

                    PrintedBook newBook = new PrintedBook(title, author, "Printed", totalPages);
                    newBook.storeBook();

                    break;
                }

                case 2: {
                    System.out.print("Please enter the title of the book: ");
                    String title = scanner.nextLine();

                    System.out.print("Please enter the author of the book: ");
                    String author = scanner.nextLine();

                    double totalLength = getValidDouble("Please enter the total length of the audio book: ");

                    AudioBook newBook = new AudioBook(title, author, "Audio", totalLength);
                    newBook.storeBook();

                    break;
                }

                case 3: {
                    return;
                }
            }
            System.out.println("");
        }
    }

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

        scanner.nextLine();

        return option;
    }

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

    public static double getValidDouble(String prompt) {
        double positiveDouble = -1.0;

        System.out.println(positiveDouble);

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

    public static void printMenu() {
        System.out.println("=================================================");
        System.out.println("                BOOK TRACKING APPLICATION        ");
        System.out.println("=================================================");
        System.out.println("Available options:");
        System.out.println("1. Add a New Printed Book");
        System.out.println("2. Add a New Audio Book");
        System.out.println("3. Exit");
        System.out.println("=================================================\n");
    }
}
