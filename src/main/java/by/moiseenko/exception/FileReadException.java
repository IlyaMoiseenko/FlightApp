package by.moiseenko.exception;

/*
    @author Ilya Moiseenko on 30.01.24
*/

public class FileReadException extends RuntimeException {

    public FileReadException(String message) {
        super(message);
    }
}
