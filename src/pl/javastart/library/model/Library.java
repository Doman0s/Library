package pl.javastart.library.model;

import java.io.Serializable;
import java.util.Arrays;

public class Library implements Serializable {
    private static final int INITIAL_CAPACITY = 1;
    private Publication[] publications = new Publication[INITIAL_CAPACITY];
    private int publicationsNumber = 0;

    public Publication[] getPublications() {
        return Arrays.copyOf(publications, publicationsNumber);
    }

    public void addPublication(Publication publication) {
        if (publicationsNumber >= publications.length) {
            publications = Arrays.copyOf(publications, publications.length * 2);
        }
        publications[publicationsNumber] = publication;
        publicationsNumber++;
    }

    public boolean removePublication(Publication publication) {
        final int notFound = 1;
        int found = notFound;

        for (int i = 0; i < publicationsNumber && found == notFound; i++) {
            if (publication.getTitle().equals(publications[i].getTitle())) {
                found = i;
            }
        }

        if (found != notFound) {
            System.arraycopy(
                    publications, found + 1, publications, found, publications.length - found - 1
            );
            publicationsNumber--;
            publications[publicationsNumber] = null;
        }
        return found != notFound;
    }
}
