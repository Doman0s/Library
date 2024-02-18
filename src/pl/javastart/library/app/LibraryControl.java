package pl.javastart.library.app;

import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;

class LibraryControl {
    private static final int EXIT = 0;
    private static final int ADD_BOOK = 1;
    private static final int ADD_MAGAZINE = 2;
    private static final int PRINT_BOOKS = 3;
    private static final int PRINT_MAGAZINES = 4;

    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    void controlLoop() {
        int option;
        do {
            printOptions();
            option = dataReader.getInt();
            processOption(option);
        } while (option != EXIT);
    }

    private void printOptions() {
        System.out.println("\nSelect an option");
        System.out.println(EXIT + " - exit application");
        System.out.println(ADD_BOOK + " - add new book");
        System.out.println(ADD_MAGAZINE + " - add new magazine");
        System.out.println(PRINT_BOOKS + " - print all available books");
        System.out.println(PRINT_MAGAZINES + " - print all available magazines");
    }

    private void processOption(int choice) {
        switch (choice) {
            case EXIT -> exit();
            case ADD_BOOK -> addBook();
            case ADD_MAGAZINE -> addMagazine();
            case PRINT_BOOKS -> printBooks();
            case PRINT_MAGAZINES -> printMagazines();
            default -> System.out.println("Incorrect option!");
        }
    }

    private void exit() {
        System.out.println("Closing library, bye!");
        dataReader.close();
    }

    private void addBook() {
        Book book = dataReader.readAndCreateBook();
        library.addBook(book);
//        System.out.println("Added successfully!");
    }

    private void printBooks() {
        library.printBooks();
    }

    private void addMagazine() {
        Magazine magazine = dataReader.readAndCreateMagazine();
        library.addMagazine(magazine);
//        System.out.println("Added successfully!");
    }

    private void printMagazines() {
        library.printMagazines();
    }
}
