package pl.javastart.library.io;

import pl.javastart.library.model.Book;

import java.util.Scanner;

public class DataReader {
    private final Scanner scanner = new Scanner(System.in);

    public Book readAndCreateBook() {
        System.out.println("Title: ");
        String title = scanner.nextLine();
        System.out.println("Author: ");
        String author = scanner.nextLine();
        System.out.println("releaseDate: ");
        int releaseDate = scanner.nextInt();
        scanner.nextLine();
        System.out.println("pages: ");
        int pages = scanner.nextInt();
        scanner.nextLine();
        System.out.println("publisher: ");
        String publisher = scanner.nextLine();
        System.out.println("isbn: ");
        String isbn = scanner.nextLine();

        return new Book(title, author, releaseDate, pages, publisher, isbn);
    }

    public void close() {
        scanner.close();
    }
}
