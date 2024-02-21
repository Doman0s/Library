package pl.javastart.library.app;

import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;

class LibraryControl {
    private DataReader dataReader = new DataReader();
    private Library library = new Library();

    void controlLoop() {
        Option option;
        do {
            printOptions();
            option = Option.createFromInt(dataReader.getInt());
            processOption(option);
        } while (option != Option.EXIT);
    }

    private void printOptions() {
        System.out.println("\nSelect an option");
        for (Option option : Option.values()) {
            System.out.println(option);
        }
    }

    private void processOption(Option option) {
        switch (option) {
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
