package classes;

import classes.Exception.Classes.*;
import interfaces.WebService;

public class User {

    String userName = "";

    public User() {
        this.userName = "testUser";
    }

    public User(String name) {
        this.userName = name;
    }

    public boolean checkIfUserIsValid(WebService service, String userName) {
        if (!service.isUserRegistered(userName)) {
            throw new UserNotRegisteredException("User not registered!");
        } else if (!service.isUserLoggedIn(userName)) {
            throw new UserNotLoggedInException("User not logged in!");
        } else {
            return true;
        }
    }

    public String refund(WebService service, int id, String userName) {
        if (!checkIfUserIsValid(service, userName)) {
            throw new NotAValidUserException("Not a valid User!");
        } else if (!service.isValidId(id)) {
            throw new NotAValidIDException("Not a valid ID!");
        } else if (!service.getArticle(id).equals("Article1")) {
            throw new WrongArticleException("Correct ID but wrong article, contact Administrator!");
        } else {
            return "REFUND GRANTED";
        }
    }
}
