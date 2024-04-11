package levy.dictionary;

import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class DictionaryGUI extends JFrame {
    private EnglishDictionary dictionary;
    private JTextArea textArea;
    private JTextField searchField;

    public DictionaryGUI() {
        try {
            dictionary = new EnglishDictionary();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        setTitle("English Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchField = new JTextField();
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateDefinitions();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateDefinitions();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateDefinitions();
            }
        });

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        add(searchField, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateDefinitions() {
        String word = searchField.getText().trim();
        if (!word.isEmpty()) {
            List<String> definitions = dictionary.getDefinition(word);
            StringBuilder sb = new StringBuilder();
            for (String definition : definitions) {
                sb.append(definition).append("\n");
            }
            textArea.setText(sb.toString());
        } else {
            textArea.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DictionaryGUI::new);
    }
}
