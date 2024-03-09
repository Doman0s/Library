package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.LibraryUser;
import pl.javastart.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsolePrinter printer;

    public DataReader(ConsolePrinter printer) {
        this.printer = printer;
    }

    public Book readAndCreateBook() {
        printer.printLine("Title: ");
        String title = scanner.nextLine();
        printer.printLine("Author: ");
        String author = scanner.nextLine();
        printer.printLine("Release year: ");
        int releaseYear = getInt();
        printer.printLine("Pages: ");
        int pages = getInt();
        printer.printLine("Publisher: ");
        String publisher = scanner.nextLine();
        printer.printLine("ISBN: ");
        String isbn = scanner.nextLine();

        return new Book(title, publisher, releaseYear, author, pages, isbn);
    }

    public Magazine readAndCreateMagazine() {
        printer.printLine("Title: ");
        String title = scanner.nextLine();
        printer.printLine("Publisher: ");
        String publisher = scanner.nextLine();
        printer.printLine("Language: ");
        String language = scanner.nextLine();
        printer.printLine("Day: ");
        int day = getInt();
        printer.printLine("Month: ");
        int month = getInt();
        printer.printLine("Release year: ");
        int releaseYear = getInt();

        return new Magazine(title, publisher, releaseYear, day, month, language);
    }

    public LibraryUser createLibraryUser() {
        printer.printLine("First name: ");
        String firstName = scanner.nextLine();
        printer.printLine("Last name: ");
        String lastName = scanner.nextLine();
        printer.printLine("Pesel: ");
        String pesel = scanner.nextLine();

        return new LibraryUser(firstName, lastName, pesel);
    }

    public int getInt() {
        try {
            return scanner.nextInt();
        } finally {
            scanner.nextLine();
        }
    }

    public String getString() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }
}
