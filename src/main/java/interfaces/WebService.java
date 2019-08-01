package interfaces;

public interface WebService {
    boolean isShopActive();
    boolean isValidId(int id);
    boolean isUserRegistered(String name);
    boolean isUserLoggedIn(String name);

    String getArticle(int id);
    String getArticle(String name);
    String getArticle(int id, String name);

    int setID(int ID, int newID);
    int getTestArticleID();

    String testArticleName = "Article1";
    int testArticleID = 001;
}
