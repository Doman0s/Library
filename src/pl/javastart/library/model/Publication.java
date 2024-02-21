package pl.javastart.library.model;

import java.util.Objects;

class Publication {
    private String title;
    private String publisher;
    private int releaseYear;

    Publication(String title, String publisher, int releaseYear) {
        this.title = title;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    int getReleaseYear() {
        return releaseYear;
    }

    void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return releaseYear == that.releaseYear && Objects.equals(title, that.title) &&
                Objects.equals(publisher, that.publisher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publisher, releaseYear);
    }

    @Override
    public String toString() {
        return title + "; " + publisher + "; " + releaseYear;
    }
}
