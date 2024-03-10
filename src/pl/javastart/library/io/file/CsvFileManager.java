package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.exception.InvalidDataException;
import pl.javastart.library.model.*;

import java.io.*;
import java.util.Collection;

class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";
    private static final String USERS_FILE_NAME = "Library_users.csv";

    @Override
    public Library importData() {
        Library library = new Library();
        importPublications(library);
        importUsers(library);
        return library;
    }

    private void importPublications(Library library) {
        try (
                FileReader fileReader = new FileReader(FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Publication publication = createPublicationFromString(line);
                library.addPublication(publication);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportException(FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataImportException("Error reading file " + FILE_NAME);
        }
    }

    private Publication createPublicationFromString(String line) {
        String[] splitLine = line.split(";");
        String type = splitLine[0];
        if (Book.TYPE.equals(type)) {
            return createBook(splitLine);
        } else if (Magazine.TYPE.equals(type)){
            return createMagazine(splitLine);
        }
        throw new InvalidDataException("Unknown type of publication " + type);
    }

    private Book createBook(String[] splitLine) {
        String title = splitLine[1];
        String publisher = splitLine[2];
        int releaseYear = Integer.parseInt(splitLine[3]);
        String author = splitLine[4];
        int pages = Integer.parseInt(splitLine[5]);
        String isbn = splitLine[6];

        return new Book(title, publisher, releaseYear, author, pages, isbn);
    }

    private Magazine createMagazine(String[] splitLine) {
        String title = splitLine[1];
        String publisher = splitLine[2];
        int releaseYear = Integer.parseInt(splitLine[3]);
        int day = Integer.parseInt(splitLine[4]);
        int month = Integer.parseInt(splitLine[5]);
        String language = splitLine[6];

        return new Magazine(title, publisher, releaseYear, day, month, language);
    }

    private void importUsers(Library library) {
        try (
                FileReader fileReader = new FileReader(USERS_FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                LibraryUser user = createUserFromString(line);
                library.addUser(user);
            }
        } catch (FileNotFoundException e) {
            throw new DataImportException(USERS_FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataImportException("Error reading file " + USERS_FILE_NAME);
        }
    }

    private LibraryUser createUserFromString(String line) {
        String[] splitLine = line.split(";");
        String firstName = splitLine[0];
        String lastName = splitLine[1];
        String pesel = splitLine[2];

        return new LibraryUser(firstName, lastName, pesel);
    }

    @Override
    public void exportData(Library library) {
        exportPublications(library);
        exportUsers(library);
    }

    private <T extends CsvConvertible> void exportToCsv(Collection<T> collection, String fileName) {
        try (
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (T t : collection) {
                bufferedWriter.write(t.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Error writing data to file " + fileName);
        }
    }

    private void exportPublications(Library library) {
        Collection<Publication> publications = library.getPublications().values();
        exportToCsv(publications, FILE_NAME);
    }

    private void exportUsers(Library library) {
        Collection<LibraryUser> users = library.getUsers().values();
        exportToCsv(users, USERS_FILE_NAME);
    }
}
