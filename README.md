# Clipboard Logger

## Description

Clipboard Logger is a Java-based clipboard monitoring application that categorizes and saves clipboard contents into different text files based on their type. The application runs in the system tray and allows users to start, pause, and exit the clipboard listener through a context menu.

## Features

- **Clipboard Monitoring**: Continuously monitors clipboard contents and categorizes them into Text, URL, Code, and Image types.
- **Category-Based Saving**: Saves clipboard data into separate text files based on content type.
- **System Tray Integration**: Start, pause, and exit the application directly from the system tray.
- **Pause/Resume Functionality**: Allows pausing and resuming the clipboard listener through the system tray menu.
- **Content Filtering**: Filters content by type using regular expressions and saves it to categorized text files.

## Technologies

- Java 8 (or higher)
- AWT (Abstract Window Toolkit) for system tray integration
- File I/O for saving content into categorized text files
- Regular expressions for detecting content types

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/HimanM/SmartClipboardManager
   ```

2. **Build the project**:
    - Use your preferred IDE (like IntelliJ IDEA or Eclipse) to open the project.
    - Ensure your project is set up to use Java 8 or higher.

3. **Run the application**:
    - Execute the `Main.java` file, or use your IDE's "Run" button.
    - The application will appear in the system tray.

## Usage

1. **Start**: Click the "Start" button from the system tray context menu to begin monitoring the clipboard.
2. **Pause**: Click the "Pause" button to pause clipboard monitoring. The button will change to "Resume" for resumption.
3. **Exit**: Click the "Exit" button to close the application.

## File Structure

- `output/`: Directory where categorized clipboard content is saved in respective text files.
- `resources/`: Folder where the system tray icon image is stored.
- `src/`: Contains Java source code files:
    - `Main.java`: Main entry point and system tray setup.
    - `ClipboardListener.java`: Listens to clipboard contents and processes them.
    - `ContentCategorizer.java`: Categorizes clipboard content.
    - `ContentSaver.java`: Saves categorized content to text files.
    - `ContentTypeDetector.java`: Detects content type using regular expressions.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.


---

