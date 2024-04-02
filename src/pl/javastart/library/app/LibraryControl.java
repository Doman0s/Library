package pl.javastart.library.app;

import pl.javastart.library.exception.*;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;
import pl.javastart.library.io.file.FileManager;
import pl.javastart.library.io.file.FileManagerBuilder;
import pl.javastart.library.model.*;

import java.util.*;

class LibraryControl {
    private final ConsolePrinter printer = new ConsolePrinter();
    private final DataReader reader = new DataReader(printer);
    private final FileManager fileManager;

    private Library library;

    LibraryControl() {
        fileManager = new FileManagerBuilder(printer, reader).build();
        try {
            library = fileManager.importData();
            printer.printLine("Data from the file has been successfully imported.");
        } catch (DataImportException | InvalidDataException e) {
            printer.printLine(e.getMessage());
            printer.printLine("A new database has been initialized.");
            library = new Library();
        }
    }

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
                option = Option.createFromInt(reader.getInt());
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
            case DELETE_BOOK -> deleteBook();
            case PRINT_BOOKS -> printBooks();
            case ADD_MAGAZINE -> addMagazine();
            case PRINT_MAGAZINES -> printMagazines();
            case DELETE_MAGAZINE -> deleteMagazine();
            case ADD_USER -> addUser();
            case PRINT_USERS -> printUsers();
            case FIND_PUBLICATION -> findPublicationByTitle();
            case EXIT -> exit();
            default -> printer.printLine("Incorrect option.");
        }
    }

    private void findPublicationByTitle() {
        printer.printLine("Enter publication title:");
        String title = reader.getString();
        String notFoundMessage = "Publication " + title + " not found.";

        library.findPublicationByTitle(title)
                .map(Publication::toString)
                .ifPresentOrElse(printer::printLine, () -> printer.printLine(notFoundMessage));
    }

    private void addBook() {
        try {
            Book book = reader.readAndCreateBook();
            library.addPublication(book);
            printer.printLine("Added successfully.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create a book, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deleteBook() {
        try {
            Book book = reader.readAndCreateBook();
            if (library.removePublication(book)) {
                printer.printLine("Book successfully deleted.");
            } else {
                printer.printLine("Book not found.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Failed to delete a book, incorrect data.");
        }
    }

    private void printBooks() {
        Collection<Publication> publications = library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER));
        printer.printBooks(publications);
    }

    private void addMagazine() {
        try {
            Magazine magazine = reader.readAndCreateMagazine();
            library.addPublication(magazine);
            printer.printLine("Added successfully.");
        } catch (InputMismatchException e) {
            printer.printLine("Failed to create a magazine, incorrect data.");
        } catch (ArrayIndexOutOfBoundsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void deleteMagazine() {
        try {
            Magazine magazine = reader.readAndCreateMagazine();
            if (library.removePublication(magazine)) {
                printer.printLine("Magazine successfully deleted.");
            } else {
                printer.printLine("Magazine not found.");
            }
        } catch (InputMismatchException e) {
            printer.printLine("Failed to delete a magazine, incorrect data.");
        }
    }

    private void printMagazines() {
        Collection<Publication> publications = library.getSortedPublications(
                Comparator.comparing(Publication::getTitle, String.CASE_INSENSITIVE_ORDER));
        printer.printMagazines(publications);
    }

    private void addUser() {
        LibraryUser user = reader.createLibraryUser();
        try {
            library.addUser(user);
            printer.printLine("Added successfully.");
        } catch (UserAlreadyExistsException e) {
            printer.printLine(e.getMessage());
        }
    }

    private void printUsers() {
        Collection<LibraryUser> users = library.getSortedUsers(
                Comparator.comparing(User::getLastName, String.CASE_INSENSITIVE_ORDER));
        printer.printUsers(users);
    }

    private void exit() {
        try {
            fileManager.exportData(library);
            printer.printLine("Export data to file completed successfully.");
        } catch (DataExportException e) {
            printer.printLine(e.getMessage());
        }
        printer.printLine("Closing library, bye.");
        reader.close();
    }

    private enum Option {
        EXIT(0, "exit application"),
        ADD_BOOK(1, "add new book"),
        DELETE_BOOK(2, "delete book"),
        ADD_MAGAZINE(3,"add new magazine"),
        DELETE_MAGAZINE(4, "delete magazine"),
        PRINT_BOOKS(5, "print all books"),
        PRINT_MAGAZINES(6, "print all magazines"),
        ADD_USER(7, "add new user"),
        PRINT_USERS(8, "print all users"),
        FIND_PUBLICATION(9, "find publication by title");

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
