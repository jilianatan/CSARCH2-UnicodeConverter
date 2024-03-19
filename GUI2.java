import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GUI {
    private JFrame frame;
    private JTabbedPane tabbedPane;
    private JPanel converterPanel;
    private JPanel translatorPanel;
    private JLabel converterResultLabel;
    private JTextField converterInputField;
    private JLabel translatorResultLabel;
    private JTextField translatorInputField;
    private converter converter;
    private translator translator;
    private ArrayList<String> results; // Store the results
    private JButton saveButton;

    public GUI() {
        frame = new JFrame("Unicode Converter and Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        converter = new converter();
        converterPanel = createConverterPanel();
        tabbedPane.addTab("Converter", converterPanel);

        translator = new translator();
        translatorPanel = createTranslatorPanel();
        tabbedPane.addTab("Translator", translatorPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);

        results = new ArrayList<>(); // Initialize the results array
    }

    private JPanel createConverterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel inputLabel = new JLabel("Enter Unicode character:");
        converterInputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(converterInputField);
        panel.add(inputPanel, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
    
        JLabel label = new JLabel("Choose Converter:");
        buttonPanel.add(label);
    
        JButton unicodeToUTF8Button = new JButton("Unicode to UTF-8");
        unicodeToUTF8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = converterInputField.getText();
                if (!input.isEmpty()) {
                    String utf8Output = converter.convertToUTF8(input);
                    converterResultLabel.setText("UTF-8 output: " + utf8Output);
                    results.add("Input: " + input + "\nResult: UTF-8 output: " + utf8Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTF8Button);
    
        JButton unicodeToUTF16Button = new JButton("Unicode to UTF-16");
        unicodeToUTF16Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = converterInputField.getText();
                if (!input.isEmpty()) {
                    String utf16Output = converter.convertToUTF16(input);
                    converterResultLabel.setText("UTF-16 output: " + utf16Output);
                    results.add("Input: " + input + "\nResult: UTF-16 output: " + utf16Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTF16Button);
    
        JButton unicodeToUTF32Button = new JButton("Unicode to UTF-32");
        unicodeToUTF32Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = converterInputField.getText();
                if (!input.isEmpty()) {
                    String utf32Output = converter.convertToUTF32(input);
                    converterResultLabel.setText("UTF-32 output: " + utf32Output);
                    results.add("Input: " + input + "\nResult: UTF-32 output: " + utf32Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTF32Button);
    
        JButton unicodeToUTFButton = new JButton("Unicode to UTF-8/16/32");
        unicodeToUTFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = converterInputField.getText();
                if (!input.isEmpty()) {
                    String utf8Output = converter.convertToUTF8(input);
                    String utf16Output = converter.convertToUTF16(input);
                    String utf32Output = converter.convertToUTF32(input);
                    converterResultLabel.setText("UTF-8: " + utf8Output +
                                            "\nUTF-16: " + utf16Output +
                                            "\nUTF-32: " + utf32Output);
                    results.add("Input: " + input + "\nResult:\nUTF-8: " + utf8Output + "\nUTF-16: " + utf16Output + "\nUTF-32: " + utf32Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTFButton);
    
        saveButton = new JButton("Save All Results");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAllResults();
            }
        });
        buttonPanel.add(saveButton);
    
        panel.add(buttonPanel, BorderLayout.WEST);
    
        converterResultLabel = new JLabel("Result will be displayed here");
        converterResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(converterResultLabel, BorderLayout.CENTER);
    
        return panel;
    }
    

    private JPanel createTranslatorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
    
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JLabel inputLabel = new JLabel("Enter UTF character:");
        translatorInputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(translatorInputField);
        panel.add(inputPanel, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1));
    
        JLabel label = new JLabel("Choose Translator:");
        buttonPanel.add(label);
    
        JButton unicodeFromUTF8Button = new JButton("UTF-8 to Unicode");
        unicodeFromUTF8Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = translatorInputField.getText();
                if (!input.isEmpty()) {
                    String unicodeOutput = translator.convertFromUTF8(input);
                    translatorResultLabel.setText("Unicode output: " + unicodeOutput);
                    results.add("Input: " + input + "\nResult: Unicode output: " + unicodeOutput + "\n");
                }
            }
        });
        buttonPanel.add(unicodeFromUTF8Button);
    
        JButton unicodeFromUTF16Button = new JButton("UTF-16 to Unicode");
        unicodeFromUTF16Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = translatorInputField.getText();
                if (!input.isEmpty()) {
                    String unicodeOutput = translator.convertFromUTF16(input);
                    translatorResultLabel.setText("Unicode output: " + unicodeOutput);
                    results.add("Input: " + input + "\nResult: Unicode output: " + unicodeOutput + "\n");
                }
            }
        });
        buttonPanel.add(unicodeFromUTF16Button);
    
        JButton unicodeFromUTF32Button = new JButton("UTF-32 to Unicode");
        unicodeFromUTF32Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = translatorInputField.getText();
                if (!input.isEmpty()) {
                    String unicodeOutput = translator.convertFromUTF32(input);
                    translatorResultLabel.setText("Unicode output: " + unicodeOutput);
                    results.add("Input: " + input + "\nResult: Unicode output: " + unicodeOutput + "\n");
                }
            }
        });
        buttonPanel.add(unicodeFromUTF32Button);
    
        JButton unicodeToUTFButton = new JButton("UTF-8/16/32 to Unicode");
        unicodeToUTFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = translatorInputField.getText();
                if (!input.isEmpty()) {
                    String utf8Output = translator.convertFromUTF8(input);
                    String utf16Output = translator.convertFromUTF16(input);
                    String utf32Output = translator.convertFromUTF32(input);
                    translatorResultLabel.setText("UTF-8: " + utf8Output +
                                            "\nUTF-16: " + utf16Output +
                                            "\nUTF-32: " + utf32Output);
                    results.add("Input: " + input + "\nResult:\nUTF-8: " + utf8Output + "\nUTF-16: " + utf16Output + "\nUTF-32: " + utf32Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTFButton);
    
        saveButton = new JButton("Save All Results");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAllResults();
            }
        });
        buttonPanel.add(saveButton);
    
        panel.add(buttonPanel, BorderLayout.WEST);
    
        translatorResultLabel = new JLabel("Result will be displayed here");
        translatorResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(translatorResultLabel, BorderLayout.CENTER);
    
        return panel;
    }
    

    private void saveAllResults() {
        String fileName = JOptionPane.showInputDialog(frame, "Enter file name:");
        if (fileName != null && !fileName.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".txt"))) {
                for (String result : results) {
                    writer.write(result + "\n");
                }
                JOptionPane.showMessageDialog(frame, "All results saved to " + fileName + ".txt");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
