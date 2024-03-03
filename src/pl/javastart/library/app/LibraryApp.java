package pl.javastart.library.app;

class LibraryApp {
    private static final String APP_NAME = "----Library v0.7----";

    public static void main(String[] args) {
        System.out.println(APP_NAME);
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.controlLoop();

//        books[0] = new Book("Astrophysics for People in a Hurry", "Neil Degrasse Tyson",
//                2017, 224, "W. W. Norton & Company", "9780393609394");
//        books[1] = new Book("Effective Java, 3rd Edition", "Joshua Bloch", 2017,
//                416, "Addison-Wesley Professional", "9780134686097");
//        books[2] = new Book("The Lady of the Lake", "Andrzej Sapkowski", 2017,
//                560, "Orbit");
    }
}
