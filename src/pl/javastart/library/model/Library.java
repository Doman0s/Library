package pl.javastart.library.model;

public class Library {
    private static final int BOOKS_LIMIT = 1000;
    private Book[] books = new Book[BOOKS_LIMIT];
    private int booksNumber = 0;

    public void addBook(Book book) {
        if (booksNumber < BOOKS_LIMIT) {
            books[booksNumber] = book;
            booksNumber++;
        } else {
            System.out.println("The maximum number of books has been reached!");
        }
    }

    public void printBooks() {
        if (booksNumber == 0) {
            System.out.println("The library is empty!");
        }
        for (int i = 0; i < booksNumber; i++) {
            books[i].printInfo();
        }
    }
}
