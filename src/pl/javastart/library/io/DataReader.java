package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.Magazine;

import java.util.Scanner;

public class DataReader {
    private final Scanner scanner = new Scanner(System.in);

    public Book readAndCreateBook() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Author: ");
        String author = scanner.nextLine();
        System.out.println("Release year: ");
        int releaseYear = getInt();
        System.out.println("Pages: ");
        int pages = getInt();
        System.out.println("Publisher: ");
        String publisher = scanner.nextLine();
        System.out.println("ISBN: ");
        String isbn = scanner.nextLine();

        return new Book(title, releaseYear, publisher, author, pages, isbn);
    }

    public Magazine readAndCreateMagazine() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Publisher: ");
        String publisher = scanner.nextLine();
        System.out.println("Language: ");
        String language = scanner.nextLine();
        System.out.println("Day: ");
        int day = getInt();
        System.out.println("Month: ");
        int month = getInt();
        System.out.println("Release year: ");
        int releaseYear = getInt();

        return new Magazine(title, publisher, language, day, month, releaseYear);
    }

    public int getInt() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public void close() {
        scanner.close();
    }
}
