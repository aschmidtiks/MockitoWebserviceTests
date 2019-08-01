package classes.Exception.Classes;

public class WrongArticleException extends RuntimeException {
    public WrongArticleException(String errorMessage) {
        super(errorMessage);
    }
}
