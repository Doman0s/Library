package pl.javastart.library.model;

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

    void printInfo() {}
}
