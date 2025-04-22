public class ClipboardManager {
    public static void main(String[] args) {
        ClipboardListener clipboardListener = new ClipboardListener(new ContentCategorizer(), new ContentSaver());
        clipboardListener.start();
    }
}
