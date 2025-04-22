import java.awt.*;
import java.awt.datatransfer.*;

public class ClipboardListener {

    private final ContentCategorizer categorizer;
    private final ContentSaver saver;

    private String lastContent = "";
    private boolean isPaused = false;  // Flag to track if the listener is paused

    public ClipboardListener(ContentCategorizer categorizer, ContentSaver saver) {
        this.categorizer = categorizer;
        this.saver = saver;
    }

    public void start() {
        System.out.println("Starting clipboard listener...");
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        while (true) {
            try {
                if (isPaused) {
                    Thread.sleep(500);  // Wait if paused
                    continue;
                }

                Transferable contents = clipboard.getContents(null);

                if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String data = (String) contents.getTransferData(DataFlavor.stringFlavor);

                    if (!data.equals(lastContent)) {
                        lastContent = data;
                        System.out.println("New clipboard content: " + data);

                        ContentType type = categorizer.categorize(data);
                        saver.save(data, type);
                    }
                }

                Thread.sleep(500);  // Check every 0.5 seconds
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Pause the clipboard listener
    public void pause() {
        isPaused = true;
        System.out.println("Clipboard listener paused.");
    }

    // Resume the clipboard listener
    public void resume() {
        isPaused = false;
        System.out.println("Clipboard listener resumed.");
    }
}
