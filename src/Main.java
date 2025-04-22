import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        // Initialize the necessary components
        ContentCategorizer categorizer = new ContentCategorizer();
        ContentSaver saver = new ContentSaver();

        // Set up the clipboard listener with dependencies
        ClipboardListener clipboardListener = new ClipboardListener(categorizer, saver);

        // Create the system tray icon
        if (SystemTray.isSupported()) {
            SystemTray systemTray = SystemTray.getSystemTray();
            TrayIcon trayIcon = createTrayIcon(clipboardListener);

            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }

            // Do not start the listener yet, only on "Start" click
        } else {
            System.out.println("System Tray not supported!");
        }
    }

    // Create the TrayIcon with a context menu
    private static TrayIcon createTrayIcon(ClipboardListener clipboardListener) {
        // Create a popup menu
        PopupMenu popupMenu = new PopupMenu();

        // Create MenuItems for the popup
        MenuItem startItem = new MenuItem("Start");
        MenuItem pauseItem = new MenuItem("Pause");
        MenuItem exitItem = new MenuItem("Exit");

        // Add action listeners to the menu items
        startItem.addActionListener(e -> {
            // Start the clipboard listener
            new Thread(() -> clipboardListener.start()).start();
            startItem.setEnabled(false);  // Disable start once it's running
            System.out.println("Clipboard listener started");
        });

        pauseItem.addActionListener(e -> {
            // If the listener is paused, resume it; if it's running, pause it
            if (pauseItem.getLabel().equals("Pause")) {
                clipboardListener.pause();   // Pause the listener
                pauseItem.setLabel("Resume");  // Change button text to "Resume"
                System.out.println("Clipboard listener paused.");
            } else {
                clipboardListener.resume();  // Resume the listener
                pauseItem.setLabel("Pause"); // Change button text to "Pause"
                System.out.println("Clipboard listener resumed.");
            }
        });

        exitItem.addActionListener(e -> System.exit(0));

        // Add the items to the popup menu
        popupMenu.add(startItem);
        popupMenu.add(pauseItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);

        // Create a tray icon with an image and popup menu
        Image image = Toolkit.getDefaultToolkit().getImage("resources/path_to_icon.png");  // Add an icon file path
        TrayIcon trayIcon = new TrayIcon(image, "Clipboard Logger");

        // Set the popup menu for the tray icon
        trayIcon.setPopupMenu(popupMenu);

        return trayIcon;
    }
}
