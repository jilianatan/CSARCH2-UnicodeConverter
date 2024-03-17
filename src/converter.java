import java.util.Scanner;

public class converter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose converter or translator:");
        System.out.println("1. Unicode to UTF-8");
        System.out.println("2. Unicode to UTF-16");
        System.out.println("3. Unicode to UTF-32");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character

        switch (choice) {
            case 1:
                System.out.print("Enter Unicode character (U+xxxx format): ");
                String unicodeInput = scanner.nextLine();
                String utf8Output = convertToUTF8(unicodeInput);
                System.out.println("UTF-8 output: " + utf8Output);
                break;
            case 2:
                System.out.print("Enter Unicode character (U+xxxx format): ");
                unicodeInput = scanner.nextLine();
                String utf16Output = convertToUTF16(unicodeInput);
                System.out.println("UTF-16 output: " + utf16Output);
                break;
            case 3:
                System.out.print("Enter Unicode character (U+xxxx format): ");
                unicodeInput = scanner.nextLine();
                String utf32Output = convertToUTF32(unicodeInput);
                System.out.println("UTF-32 output: " + utf32Output);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        scanner.close();
    }

    public static String convertToUTF8(String hexInput) {
        // Removing "U+" if present and padding with zeroes
        hexInput = hexInput.replace("U+", "").replaceAll("^0+", "");
        if (hexInput.isEmpty()) hexInput = "0";

        // Converting hex to decimal
        int decInput = Integer.parseInt(hexInput, 16);

        // Convert to UTF-8
        if (decInput >= 0 && decInput <= 127) {
            return String.format("%02X", decInput); // Single-byte character, return the hex value
        } else if (decInput <= 2047) {
            int b1 = 0xC0 | (decInput >> 6); // First byte
            int b2 = 0x80 | (decInput & 0x3F); // Second byte
            return String.format("%02X %02X", b1, b2); // Return both bytes separated by space
        } else if (decInput <= 65535) {
            int b1 = 0xE0 | (decInput >> 12); // First byte
            int b2 = 0x80 | ((decInput >> 6) & 0x3F); // Second byte
            int b3 = 0x80 | (decInput & 0x3F); // Third byte
            return String.format("%02X %02X %02X", b1, b2, b3); // Return all three bytes separated by space
        } else {
            return "Invalid Unicode character"; // Out of UTF-8 range
        }
    }

    public static String convertToUTF16(String hexInput) {
        // Removing "U+" if present and padding with zeroes
        hexInput = hexInput.replace("U+", "").replaceAll("^0+", "");
        if (hexInput.isEmpty()) hexInput = "0";

        // Converting hex to decimal
        int decInput = Integer.parseInt(hexInput, 16);

        // Convert to UTF-16
        if (decInput >= 0 && decInput <= 65535) {
            return String.format("%04X", decInput); // Single code unit, return the hex value
        } else if (decInput <= 1114111) {
            decInput -= 0x10000; // Subtract the offset
            int highSurrogate = 0xD800 | (decInput >> 10); // Calculate the high surrogate
            int lowSurrogate = 0xDC00 | (decInput & 0x3FF); // Calculate the low surrogate
            return String.format("%04X %04X", highSurrogate, lowSurrogate); // Return both surrogates separated by space
        } else {
            return "Invalid Unicode character"; // Out of UTF-16 range
        }
    }

    public static String convertToUTF32(String hexInput) {
        // Removing "U+" if present and padding with zeroes
        hexInput = hexInput.replace("U+", "").replaceAll("^0+", "");
        if (hexInput.isEmpty()) hexInput = "0";

        // Converting hex to decimal and return as is
        int decInput = Integer.parseInt(hexInput, 16);
        return String.format("%08X", decInput); // Return the hex value padded to 8 characters
    }
}
