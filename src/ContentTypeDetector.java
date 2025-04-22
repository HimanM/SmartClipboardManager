public class ContentTypeDetector {

    public ContentType detect(String content) {
        if (content.matches("(?i)^(https?|ftp)://[\\w\\-._~:/?#\\[\\]@!$&'()*+,;=%.]+$")) {
            return ContentType.URL;
        } else if (content.matches("(?s).*(;|\\{|\\}|public|class|def|if|else|for|while).*")) {
            return ContentType.CODE;
        } else if (content.trim().isEmpty()) {
            return ContentType.IMAGE;
        } else {
            return ContentType.TEXT;
        }
    }
}
