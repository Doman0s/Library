package pl.javastart.library.app;

import pl.javastart.library.exception.NoSuchOptionException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;

import java.util.InputMismatchException;

class LibraryControl {
    private ConsolePrinter printer = new ConsolePrinter();
    private DataReader dataReader = new DataReader(printer);
    private Library library = new Library();

    void controlLoop() {
        Option option;
        do {
            printOptions();
            option = getOption();
            processOption(option);
        } while (option != Option.EXIT);
    }

    private void printOptions() {
        printer.printLine("\nSelect an option");
        for (Option option : Option.values()) {
            printer.printLine(option.toString());
        }
    }

    private Option getOption() {
        boolean optionOk = false;
        Option option = null;

        do {
            try {
                option = Option.createFromInt(dataReader.getInt());
                optionOk = true;
            } catch (NoSuchOptionException e) {
                printer.printLine(e.getMessage());
            } catch (InputMismatchException e) {
                printer.printLine("A value was entered that is not a number, enter again:");
            }
        } while (!optionOk);

        return option;
    }

    private void processOption(Option option) {
        switch (option) {
            case ADD_BOOK -> addBook();
            case PRINT_BOOKS -> printBooks();
            case ADD_MAGAZINE -> addMagazine();
            case PRINT_MAGAZINES -> printMagazines();
            case EXIT -> exit();
            default -> printer.printLine("Incorrect option.");
        }
    }

    private void addBook() {
        try {
            Book book = dataReader.readAndCreateBook();
            library.addBook(book);
            printer.printLine("Added successfully.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create a book, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printBooks() {
        printer.printBooks(library.getPublications());
    }

    private void addMagazine() {
        try {
            Magazine magazine = dataReader.readAndCreateMagazine();
            library.addMagazine(magazine);
            printer.printLine("Added successfully.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create a magazine, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printMagazines() {
        printer.printMagazines(library.getPublications());
    }

    private void exit() {
        printer.printLine("Closing library, bye.");
        dataReader.close();
    }

    private enum Option {
        EXIT(0, "exit application"),
        ADD_BOOK(1, "add new book"),
        ADD_MAGAZINE(2,"add new magazine"),
        PRINT_BOOKS(3, "print all available books"),
        PRINT_MAGAZINES(4, "print all available magazines");

        private final int value;
        private final String description;

        Option(int value, String description) {
            this.value = value;
            this.description = description;
        }

        static Option createFromInt(int option) throws NoSuchOptionException {
            try {
                return Option.values()[option];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchOptionException("No option with id " + option);
            }
        }

        @Override
        public String toString() {
            return value + " - " + description;
        }
    }
}
