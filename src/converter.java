import java.util.Scanner;

public class converter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose UTF:");
        System.out.println("1. UTF-8");
        System.out.println("2. UTF-16");
        System.out.println("3. UTF-32");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter UTF-8 input: ");
                String utf8Input = scanner.nextLine();
                String unicodeOutput = convertToUnicodeFromUTF8(utf8Input);
                System.out.println("Unicode output: " + unicodeOutput);
                break;
            case 2:
                System.out.print("Enter UTF-16 input: ");
                String utf16Input = scanner.nextLine();
                unicodeOutput = convertToUnicodeFromUTF16(utf16Input);
                System.out.println("Unicode output: " + unicodeOutput);
                break;
            case 3:
                System.out.print("Enter UTF-32 input: ");
                String utf32Input = scanner.nextLine();
                unicodeOutput = convertToUnicodeFromUTF32(utf32Input);
                System.out.println("Unicode output: " + unicodeOutput);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static String convertToUnicodeFromUTF8(String utf8Input) {
        try {
            String[] bytes = utf8Input.split(" ");
            StringBuilder unicodeOutput = new StringBuilder();
            for (String byteStr : bytes) {
                int decimalValue = Integer.parseInt(byteStr, 16);
                unicodeOutput.append(Character.toChars(decimalValue));
            }
            return unicodeOutput.toString();
        } catch (NumberFormatException e) {
            System.out.println("Error converting UTF-8 to Unicode: " + e.getMessage());
            return "";
        }
    }

    public static String convertToUnicodeFromUTF16(String utf16Input) {
        try {
            String[] codes = utf16Input.split(" ");
            StringBuilder unicodeOutput = new StringBuilder();
            for (int i = 0; i < codes.length; i += 2) {
                int highSurrogate = Integer.parseInt(codes[i], 16);
                int lowSurrogate = Integer.parseInt(codes[i + 1], 16);
                int codepoint = Character.toCodePoint((char) highSurrogate, (char) lowSurrogate);
                unicodeOutput.append(Character.toChars(codepoint));
            }
            return unicodeOutput.toString();
        } catch (NumberFormatException e) {
            System.out.println("Error converting UTF-16 to Unicode: " + e.getMessage());
            return "";
        }
    }

    public static String convertToUnicodeFromUTF32(String utf32Input) {
        try {
            int codepoint = Integer.parseInt(utf32Input, 16);
            return new String(Character.toChars(codepoint));
        } catch (NumberFormatException e) {
            System.out.println("Error converting UTF-32 to Unicode: " + e.getMessage());
            return "";
        }
    }
}
