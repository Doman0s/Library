package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.LibraryUser;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {
    public void printBooks(Collection<Publication> publications) {
        if (publications.isEmpty()) {
            printLine("No books in the library!");
        }
        for (Publication publication : publications) {
            if (publication instanceof Book) {
                printLine(publication.toString());
            }
        }
    }

    public void printMagazines(Collection<Publication> publications) {
        if (publications.isEmpty()) {
            printLine("No magazines in the library!");
        }
        for (Publication publication : publications) {
            if (publication instanceof Magazine) {
                printLine(publication.toString());
            }
        }
    }

    public void printUsers(Collection<LibraryUser> users) {
        if (users.isEmpty()) {
            printLine("No users in the library!");
        }
        for (LibraryUser user : users) {
            printLine(user.toString());
        }
    }

    public void printLine(String text) {
        System.out.println(text);
    }
}
