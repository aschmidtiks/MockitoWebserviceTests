package classes.Exception.Classes;

public class NotAValidUserException extends RuntimeException {
    public NotAValidUserException(String errorMessage) {
        super(errorMessage);
    }
}
