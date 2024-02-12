package pl.javastart.library.app;

import pl.javastart.library.model.Book;

class Library {
    public static void main(String[] args) {
        final String appName = "Library v0.2";

        Book book1 = new Book("Astrophysics for People in a Hurry", "Neil Degrasse Tyson",
                2017, 224, "W. W. Norton & Company", "9780393609394");
        Book book2 = new Book("Effective Java, 3rd Edition", "Joshua Bloch", 2017,
                416, "Addison-Wesley Professional", "9780134686097");
        Book book3 = new Book("The Lady of the Lake", "Andrzej Sapkowski", 2017,
                560, "Orbit");

        System.out.println(appName);
        System.out.println("Books available in the library");
        book1.printInfo();
        book2.printInfo();
        book3.printInfo();
    }
}
