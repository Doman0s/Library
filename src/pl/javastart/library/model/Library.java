package pl.javastart.library.model;

public class Library {
    private static final int PUBLICATIONS_LIMIT = 2000;
    private Publication[] publications = new Publication[PUBLICATIONS_LIMIT];
    private int publicationsNumber = 0;

    public void addBook(Book book) {
        if (publicationsNumber < PUBLICATIONS_LIMIT) {
            publications[publicationsNumber] = book;
            publicationsNumber++;
        } else {
            System.out.println("The maximum number of books has been reached!");
        }
    }

    public void addMagazine(Magazine magazine) {
        if (publicationsNumber < PUBLICATIONS_LIMIT) {
            publications[publicationsNumber] = magazine;
            publicationsNumber++;
        } else {
            System.out.println("The maximum number of magazines has been reached!");
        }
    }

    public void printBooks() {
        int countBooks = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Book) {
                System.out.println(publications[i]);
                countBooks++;
            }
        }
        if (countBooks == 0) {
            System.out.println("No books in the library!");
        }
    }

    public void printMagazines() {
        int countMagazines = 0;
        for (int i = 0; i < publicationsNumber; i++) {
            if (publications[i] instanceof Magazine) {
                countMagazines++;
            }
        }
        if (countMagazines == 0) {
            System.out.println("No magazines in the library!");
        }
    }
}
