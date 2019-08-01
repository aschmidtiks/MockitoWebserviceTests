package classes.Exception.Classes;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException(String errorMessage) {
        super(errorMessage);
    }
}
