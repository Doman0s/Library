package pl.javastart.library.model;

public class Library {
    private static final int PUBLICATIONS_LIMIT = 2000;
    private Publication[] publications = new Publication[PUBLICATIONS_LIMIT];
    private int publicationsNumber = 0;

    public Publication[] getPublications() {
        Publication[] result = new Publication[publicationsNumber];
        for (int i = 0; i < publicationsNumber; i++) {
            result[i] = publications[i];
        }
        return result;
    }

    public void addBook(Book book) {
        addPublication(book);
    }

    public void addMagazine(Magazine magazine) {
        addPublication(magazine);
    }

    private void addPublication(Publication publication) {
        if (publicationsNumber >= PUBLICATIONS_LIMIT) {
            throw new ArrayIndexOutOfBoundsException(
                    "The maximum number of publications has been reached. The limit is " + PUBLICATIONS_LIMIT
            );
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;
    }
}
