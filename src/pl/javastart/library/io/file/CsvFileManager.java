package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.exception.InvalidDataException;
import pl.javastart.library.model.Book;
import pl.javastart.library.model.Library;
import pl.javastart.library.model.Magazine;
import pl.javastart.library.model.Publication;

import java.io.*;

class CsvFileManager implements FileManager {
    private static final String FILE_NAME = "Library.csv";

    @Override
    public Library importData() {
        Library library = new Library();

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

        return library;
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

    @Override
    public void exportData(Library library) {
        Publication[] publications = library.getPublications();
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            for (Publication publication : publications) {
                bufferedWriter.write(publication.toCsv());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new DataExportException("Error writing data to file " + FILE_NAME);
        }
    }
}
