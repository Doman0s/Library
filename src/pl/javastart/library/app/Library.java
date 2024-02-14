package pl.javastart.library.app;

import pl.javastart.library.model.Book;

class Library {
    public static void main(String[] args) {
        final String appName = "Library v0.3";

        Book[] books = new Book[1000];
        books[0] = new Book("Astrophysics for People in a Hurry", "Neil Degrasse Tyson",
                2017, 224, "W. W. Norton & Company", "9780393609394");
        books[1] = new Book("Effective Java, 3rd Edition", "Joshua Bloch", 2017,
                416, "Addison-Wesley Professional", "9780134686097");
        books[2] = new Book("The Lady of the Lake", "Andrzej Sapkowski", 2017,
                560, "Orbit");

        System.out.println(appName);
        System.out.println("Books available in the library");
        books[0].printInfo();
        books[1].printInfo();
        books[2].printInfo();
        System.out.println("The maximum number of books in the library is " + books.length);
    }
}
