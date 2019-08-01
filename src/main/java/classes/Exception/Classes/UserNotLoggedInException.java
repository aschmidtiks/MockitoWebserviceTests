package classes.Exception.Classes;

public class UserNotLoggedInException extends RuntimeException{
    public UserNotLoggedInException(String errorMessage) {
        super(errorMessage);
    }
}
