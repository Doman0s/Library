package pl.javastart.library.io.file;

import pl.javastart.library.exception.NoSuchFileTypeException;
import pl.javastart.library.io.ConsolePrinter;
import pl.javastart.library.io.DataReader;

public class FileManagerBuilder {
    private final ConsolePrinter printer;
    private final DataReader reader;

    public FileManagerBuilder(ConsolePrinter printer, DataReader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    public FileManager build() {
        printer.printLine("Select data format: ");
        FileType fileType = getFileType();

        return switch (fileType) {
            case SERIAL -> new SerializableFileManager();
            case CSV -> new CsvFileManager();
            default -> throw new NoSuchFileTypeException("Unsupported data type.");
        };
    }

    private FileType getFileType() {
        boolean typeOk = false;
        FileType result = null;

        do {
            printTypes();
            String type = reader.getString().toUpperCase();
            try {
                result = FileType.valueOf(type);
                typeOk = true;
            } catch (IllegalArgumentException e) {
                printer.printLine("Unsupported data type, select again.");
            }
        } while (!typeOk);

        return result;
    }

    private void printTypes() {
        for (FileType value : FileType.values()) {
            printer.printLine(value.toString());
        }
    }
}
