import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public GUI() {
        frame = new JFrame("Unicode Converter and Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        converter = new converter(); // Creating an instance of Converter
        converterPanel = createConverterPanel();
        tabbedPane.addTab("Converter", converterPanel);

        translator = new translator();
        translatorPanel = createTranslatorPanel();
        tabbedPane.addTab("Translator", translatorPanel);

        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setVisible(true);
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
                }
            }
        });
        buttonPanel.add(unicodeToUTFButton);

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
                }
            }
        });
        buttonPanel.add(unicodeFromUTF32Button);

        panel.add(buttonPanel, BorderLayout.WEST);

        translatorResultLabel = new JLabel("Result will be displayed here");
        translatorResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(translatorResultLabel, BorderLayout.CENTER);

        return panel;
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
