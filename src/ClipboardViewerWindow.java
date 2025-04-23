import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

public class ClipboardViewerWindow extends JFrame {
    private final File outputDir = new File("output");

    public ClipboardViewerWindow(Runnable onClose) {
        setTitle("Clipboard Viewer");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        // Dynamically create tabs based on folders inside 'output'
        if (outputDir.exists() && outputDir.isDirectory()) {
            File[] folders = outputDir.listFiles(File::isDirectory);
            if (folders != null) {
                for (File folder : folders) {
                    tabs.add(folder.getName(), createTab(folder));
                }
            }
        }

        add(tabs);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onClose.run();
            }
        });
    }

    private JPanel createTab(File folder) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        try {
            List<File> files = Files.list(folder.toPath())
                    .filter(p -> p.toString().endsWith(".txt"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            for (File file : files) {
                String content = new String(Files.readAllBytes(file.toPath()));
                JPanel row = new JPanel(new BorderLayout());
                JTextArea text = new JTextArea(content);
                text.setLineWrap(true);
                text.setWrapStyleWord(true);
                text.setEditable(false);

                JButton copyButton = new JButton("Copy");
                copyButton.addActionListener(e -> {
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(content), null);
                });

                row.add(new JScrollPane(text), BorderLayout.CENTER);
                row.add(copyButton, BorderLayout.EAST);
                panel.add(row);
            }

            JButton clearButton = new JButton("Clear Folder");
            clearButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + folder.getName() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteFolder(folder);
                    JOptionPane.showMessageDialog(this, folder.getName() + " cleared. Please reopen viewer to refresh.");
                }
            });

            panel.add(Box.createVerticalStrut(10));
            panel.add(clearButton);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return panel;
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) f.delete();
        }
        folder.delete();
    }
}
