public class ContentCategorizer {

    private final ContentTypeDetector typeDetector;

    public ContentCategorizer() {
        this.typeDetector = new ContentTypeDetector();
    }

    public ContentType categorize(String content) {
        return typeDetector.detect(content);
    }
}
