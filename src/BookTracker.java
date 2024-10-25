import java.util.Scanner;

public class BookTracker {

    private static final Scanner scanner = new Scanner(System.in);
    private static final int totalMenuOptions = 2;

    public static void main(String[] args) {
        Book.readBookList();

        printMenu();

        while (true) {

            int option = getOption();

            switch(option) {
                case 1: {
                    break;
                }

                case 2: {
                    return;
                }
            }
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

        return option;
    }

    public static void printMenu() {
        System.out.println("=================================================");
        System.out.println("                BOOK TRACKING APPLICATION        ");
        System.out.println("=================================================");
        System.out.println("Available options:");
        System.out.println("1. Do stuff");
        System.out.println("2. Exit");
        System.out.println("=================================================\n");
    }
}
