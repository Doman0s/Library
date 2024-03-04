package pl.javastart.library.io.file;

import pl.javastart.library.exception.DataExportException;
import pl.javastart.library.exception.DataImportException;
import pl.javastart.library.model.Library;

import java.io.*;

class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "Library.obj";

    @Override
    public Library importData() {
        Library library;

        try (
                FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            library = (Library) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException(FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataImportException("Error reading file " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Incompatible data type in file " + FILE_NAME);
        }
        return library;
    }

    @Override
    public void exportData(Library library) {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException(FILE_NAME + " not found");
        } catch (IOException e) {
            throw new DataExportException("Error writing data to file " + FILE_NAME);
        }
    }
}
