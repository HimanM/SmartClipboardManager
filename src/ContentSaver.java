import java.io.*;

public class ContentSaver {

    private static final String MAIN_LOG_FILE = "output/main_log.txt"; // Main log file

    // Save content to the appropriate category-specific log file
    public void save(String content, ContentType category) {
        try {
            // Create the category-specific directory if it doesn't exist
            File categoryDir = new File("output/" + category);
            if (!categoryDir.exists()) {
                categoryDir.mkdirs(); // Create category folder
            }

            // Create or open the category-specific log file
            File categoryFile = new File(categoryDir, "clipboard_log.txt");

            // Append the content to the category-specific log file
            try (FileWriter categoryWriter = new FileWriter(categoryFile, true)) {
                categoryWriter.write(content + "\n\n");
            }

            // Append the content to the main log file
            try (FileWriter mainWriter = new FileWriter(MAIN_LOG_FILE, true)) {
                mainWriter.write("Category: " + category + "\n" + content + "\n\n");
            }

            System.out.println("Saved content to category: " + category);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
