public interface WebService {
    boolean isShopActive();
    String getArticleByID(int id);
    int articleID = 0;
    boolean isValidId(int id);
    String articleName = "Article1";
}
