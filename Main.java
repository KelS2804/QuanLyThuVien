package Library;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static BookManager bookMan = new BookManager();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome! Choose one of these options to continue: ");
        while (true) {
            Menu();
            int option = 0;
            boolean validOption = false;

            // Get user's option
            while (!validOption) {
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.print("Please enter a number from 0 to 6: ");
                    scanner.nextLine();
                    continue;
                }
                if (option < 0 || option > 6) {
                    System.out.print("Please enter a number from 0 to 6: ");
                    continue;
                }
                validOption = true;
            }

            processOption(option);
            if (option == 0) break;
        }

        System.out.println("GoodBye!");
    }

    public static void processOption(int option) {
        switch (option) {
            case 0:{
            	bookMan.saveToFile();
            }
            case 1: {
            	bookMan.printBooks(bookMan.getBooks());
            }

            // Add a new book
            case 2: {
                try {
                    System.out.print("Enter book id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (id <= 0) {
                        System.out.println("Id should be a positive number");
                        break;
                    }

                    System.out.print("Enter book name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter book price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    if (price < 0) price = 0;

                    if (bookMan.addBook(new Book(id, name, price))) System.out.println("Added successfully");
                    else System.out.println("There is already a book with id " + id);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            }

            // Edit book
            case 3: {
                try {
                    System.out.print("Enter book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (id <= 0) {
                        System.out.println("ID should be positive number");
                        break;
                    }

                    Book book = bookMan.getBookById(id);
                    if (book == null) break;
                    System.out.print("Enter book name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter book price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    if (price < 0) price = 0;
                    book.setName(name);
                    book.setPrice(price);
                    System.out.println("Updated successfully");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input");
                    scanner.nextLine();
                }
            }

            // Delete book
            case 4 : {
                try {
                    System.out.print("Enter book id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Book book = bookMan.getBookById(id);
                    if (book == null) break;
                    bookMan.removeBook(book);
                    System.out.println("Deleted successfully!");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid ID!");
                    scanner.nextLine();
                }
            }

            // Search book with keyword
            case 5 : {
                System.out.print("Enter keyword: ");
                String keyword = scanner.nextLine();
                bookMan.printBooks(bookMan.searchByName(keyword));
            }

            // Sort book by price in descending order
            case 6 : {
                bookMan.sortDescByPrice();
                System.out.println("After sorting: ");
                bookMan.printBooks(bookMan.getBooks());
            }
        }
    }

    public static void Menu() {
        System.out.println("""
                -----------------------------------
                1. list all books
                2. add a new book
                3. edit book
                4. delete a book
                5. search books by name
                6. sort books descending by price
                0. save & exit
                -----------------------------------""");
        System.out.print("Your option: ");
    }
}
