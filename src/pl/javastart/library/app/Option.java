package pl.javastart.library.app;

import pl.javastart.library.exception.NoSuchOptionException;

enum Option {
    EXIT(0, "exit application"),
    ADD_BOOK(1, "add new book"),
    ADD_MAGAZINE(2,"add new magazine"),
    PRINT_BOOKS(3, "print all available books"),
    PRINT_MAGAZINES(4, "print all available magazines");

    private final int value;
    private final String description;

    Option(int value, String description) {
        this.value = value;
        this.description = description;
    }

    int getValue() {
        return value;
    }

    String getDescription() {
        return description;
    }

    static Option createFromInt(int option) throws NoSuchOptionException {
        try {
            return Option.values()[option];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoSuchOptionException("No option with id " + option);
        }
    }

    @Override
    public String toString() {
        return value + " - " + description;
    }
}
