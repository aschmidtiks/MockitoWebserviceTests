package classes.Exception.Classes;

public class NotAValidIDException extends RuntimeException {
    public NotAValidIDException(String errorMessage) {
        super(errorMessage);
    }
}
