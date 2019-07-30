public class User {
    public String refund(WebService service, int id) {
        if (service.isValidId(id)) {
            if (service.getArticleByID(id) == "Article1") {
                return "REFUND GRANTED";
            }
        }
        return "NOT GRANTED";
    }
}
