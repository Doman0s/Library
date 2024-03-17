package pl.javastart.library.io;

import pl.javastart.library.model.Book;
import pl.javastart.library.model.LibraryUser;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.util.Collection;

public class ConsolePrinter {
    public void printBooks(Collection<Publication> publications) {
        long countBooks = publications.stream()
                .filter(publication -> publication instanceof Book)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (countBooks == 0) {
            printLine("No books in the library!");
        }
    }

    public void printMagazines(Collection<Publication> publications) {
        long countMagazines = publications.stream()
                .filter(publication -> publication instanceof Magazine)
                .map(Publication::toString)
                .peek(this::printLine)
                .count();
        if (countMagazines == 0) {
            printLine("No magazines in the library!");
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
