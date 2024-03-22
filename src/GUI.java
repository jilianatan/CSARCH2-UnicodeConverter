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
    private ArrayList<String> results; 
    private JButton saveButton;

    public GUI() {
        frame = new JFrame("Unicode Converter and Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());
        
        // Create the main panel that holds the tabbed panes
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F5F5DC")); // Change background color here
        
        tabbedPane = new JTabbedPane();
        tabbedPane.setUI(new RoundedTabbedPaneUI());
        
        converter = new converter();
        converterPanel = createConverterPanel();
        tabbedPane.addTab("Converter", converterPanel);
        tabbedPane.setForeground(Color.white);
        translator = new translator();
        translatorPanel = createTranslatorPanel();
        tabbedPane.addTab("Translator", translatorPanel);
        
        mainPanel.add(tabbedPane, BorderLayout.CENTER); // Add tabbed pane to the main panel

        frame.add(mainPanel, BorderLayout.CENTER); // Add the main panel to the frame
        frame.setVisible(true);
        
        results = new ArrayList<>(); 
    }

    private JPanel createConverterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(Color.decode("#F5F5DC"));;
        JLabel inputLabel = new JLabel("Enter Unicode character:");
        inputLabel.setForeground(Color.decode("#665651"));
        converterInputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(converterInputField);
        panel.add(inputPanel, BorderLayout.NORTH);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setBackground(Color.decode("#F5F5DC"));;
        JLabel label = new JLabel("Choose Converter:");
        label.setForeground(Color.decode("#665651"));
        buttonPanel.add(label);
    
        JButton unicodeToUTF8Button = new RoundButton("Unicode to UTF-8");
        unicodeToUTF8Button.setForeground(Color.white);
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
    
        JButton unicodeToUTF16Button = new RoundButton("Unicode to UTF-16");
        unicodeToUTF16Button.setForeground(Color.white);
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
    
        JButton unicodeToUTF32Button = new RoundButton("Unicode to UTF-32");
        unicodeToUTF32Button.setForeground(Color.white);
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
    
        JButton unicodeToUTFButton = new RoundButton("Unicode to UTF-8/16/32");
        unicodeToUTFButton.setForeground(Color.white);
        unicodeToUTFButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = converterInputField.getText();
                if (!input.isEmpty()) {
                    String utf8Output = converter.convertToUTF8(input);
                    String utf16Output = converter.convertToUTF16(input);
                    String utf32Output = converter.convertToUTF32(input);
                    converterResultLabel.setText("<html>UTF-8: " + utf8Output +
                                            "<br>UTF-16: " + utf16Output +
                                            "<br>UTF-32: " + utf32Output + "</html>");
                    results.add("Input: " + input + "\nResult:\nUTF-8: " + utf8Output + "\nUTF-16: " + utf16Output + "\nUTF-32: " + utf32Output + "\n");
                }
            }
        });
        buttonPanel.add(unicodeToUTFButton);
    
        JPanel resultAndButtonPanel = new JPanel();
        resultAndButtonPanel.setLayout(new BoxLayout(resultAndButtonPanel, BoxLayout.Y_AXIS));
        resultAndButtonPanel.setBackground(Color.decode("#F5F5DC"));;
        converterResultLabel = new JLabel("Result will be displayed here");
        converterResultLabel.setForeground(Color.decode("#665651"));
        converterResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label horizontally
        converterResultLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label text
        resultAndButtonPanel.add(converterResultLabel);
        
        resultAndButtonPanel.add(Box.createVerticalStrut(10)); // Adjust the space here

        saveButton = new RoundButton("Save All Results");
        saveButton.setForeground(Color.white);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button horizontally
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            saveAllResults();
            }
        });
        resultAndButtonPanel.add(saveButton);

        JPanel centerPanel = new JPanel(new GridBagLayout()); // Panel to center resultAndButtonPanel
        centerPanel.add(resultAndButtonPanel);
        centerPanel.setBackground(Color.decode("#F5F5DC"));;
        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER); // Center the resultAndButtonPanel

        return panel;
    }
    

    private JPanel createTranslatorPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(Color.decode("#F5F5DC"));;
        JLabel inputLabel = new JLabel("Enter UTF character:");
        inputLabel.setForeground(Color.decode("#665651"));
        translatorInputField = new JTextField(10);
        inputPanel.add(inputLabel);
        inputPanel.add(translatorInputField);
        panel.add(inputPanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setBackground(Color.decode("#F5F5DC"));;

        JLabel label = new JLabel("Choose Translator:");
        label.setForeground(Color.decode("#665651"));

        buttonPanel.add(label);
        
        JButton unicodeFromUTF8Button = new RoundButton("UTF-8 to Unicode");
        unicodeFromUTF8Button.setForeground(Color.white);
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
        
        JButton unicodeFromUTF16Button = new RoundButton("UTF-16 to Unicode");
        unicodeFromUTF16Button.setForeground(Color.white);
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
        
        JButton unicodeFromUTF32Button = new RoundButton("UTF-32 to Unicode");
        unicodeFromUTF32Button.setForeground(Color.white);
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
       
        JPanel resultAndButtonPanel = new JPanel();
        resultAndButtonPanel.setLayout(new BoxLayout(resultAndButtonPanel, BoxLayout.Y_AXIS));
        resultAndButtonPanel.setBackground(Color.decode("#F5F5DC"));
        translatorResultLabel = new JLabel("Result will be displayed here");
        translatorResultLabel.setForeground(Color.decode("#665651"));
        translatorResultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        translatorResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultAndButtonPanel.add(translatorResultLabel);

        resultAndButtonPanel.add(Box.createVerticalStrut(10));

        saveButton = new RoundButton("Save All Results");
        saveButton.setForeground(Color.white);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAllResults();
            }
        });

        resultAndButtonPanel.add(saveButton);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(resultAndButtonPanel);
        centerPanel.setBackground(Color.decode("#F5F5DC"));
        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);

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
