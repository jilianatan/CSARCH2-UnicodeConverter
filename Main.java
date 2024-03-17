import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose Converter or Translator:");
        System.out.println("1. Converter");
        System.out.println("2. Translator");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                System.out.println("Choose Converter:");
                System.out.println("1. Unicode to UTF-8");
                System.out.println("2. Unicode to UTF-16");
                System.out.println("3. Unicode to UTF-32");
                System.out.println("4. Convert to UTF-8/16/32");
                System.out.print("Enter your choice: ");

                int converterChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (converterChoice) {
                    case 1:
                        System.out.print("Enter Unicode character: ");
                        String unicodeInput = scanner.nextLine();
                        String utf8Output = converter.convertToUTF8(unicodeInput);
                        System.out.println("UTF-8 output: " + utf8Output);
                        break;
                    case 2:
                        System.out.print("Enter Unicode character: ");
                        unicodeInput = scanner.nextLine();
                        String utf16Output = converter.convertToUTF16(unicodeInput);
                        System.out.println("UTF-16 output: " + utf16Output);
                        break;
                    case 3:
                        System.out.print("Enter Unicode character: ");
                        unicodeInput = scanner.nextLine();
                        String utf32Output = converter.convertToUTF32(unicodeInput);
                        System.out.println("UTF-32 output: " + utf32Output);
                        break;
                    case 4:
                        System.out.print("Enter Unicode character (xxxxxxxx format): ");
                        unicodeInput = scanner.nextLine();
                        String utf8Output4 = converter.convertToUTF8(unicodeInput);
                        String utf16Output4 = converter.convertToUTF16(unicodeInput);
                        String utf32Output4 = converter.convertToUTF32(unicodeInput);
                        System.out.println("UTF-8 output: " + utf8Output4);
                        System.out.println("UTF-16 output: " + utf16Output4);
                        System.out.println("UTF-32 output: " + utf32Output4);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

                break;
            case 2:
                System.out.println("Choose Translator:");
                System.out.println("1. UTF-8 to Unicode");
                System.out.println("2. UTF-16 to Unicode");
                System.out.println("3. UTF-32 to Unicode");
                System.out.print("Enter your choice: ");

                int translatorChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (translatorChoice) {
                    case 1:
                        System.out.print("Enter UTF-8 (xx xx xx xx format): ");
                        String UTFInput = scanner.nextLine();
                        String UTFOutput = translator.convertFromUTF8(UTFInput);
                        System.out.println("UTF-8 output: " + UTFOutput);
                        break;
                    case 2:
                        System.out.print("Enter Unicode character (xxxx xxxx format): ");
                        UTFInput = scanner.nextLine();
                        String utf16Output = translator.convertFromUTF16(UTFInput);
                        System.out.println("UTF-16 output: " + utf16Output);
                        break;
                    case 3:
                        System.out.print("Enter Unicode character (xxxxxxxx format): ");
                        UTFInput = scanner.nextLine();
                        String utf32Output = translator.convertFromUTF32(UTFInput);
                        System.out.println("UTF-32 output: " + utf32Output);
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }
}
