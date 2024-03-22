public class translator {
    public String convertFromUTF8(String utf8Input) {
        // Validate input format (XX XX XX XX)
        if (!utf8Input.matches("^[0-9A-Fa-f]{2}( [0-9A-Fa-f]{2})+$")) {
            return "Invalid input format. Please enter XX XX XX XX format.";
        }

        // Split the input into separate bytes
        String[] utf8Bytes = utf8Input.trim().split("\\s+");
        StringBuilder unicodeBuilder = new StringBuilder();

        try {
            // Iterate through each UTF-8 byte
            int i = 0;
            while (i < utf8Bytes.length) {
                // Parse the byte as an integer
                int byteValue = Integer.parseInt(utf8Bytes[i], 16);

                // Determine the number of continuation bytes based on the start byte
                int continuationBytes;
                if ((byteValue & 0x80) == 0) {
                    continuationBytes = 0; // Single-byte character
                } else if ((byteValue & 0xE0) == 0xC0) {
                    continuationBytes = 1; // Two-byte character
                } else if ((byteValue & 0xF0) == 0xE0) {
                    continuationBytes = 2; // Three-byte character
                } else if ((byteValue & 0xF8) == 0xF0) {
                    continuationBytes = 3; // Four-byte character
                } else {
                    return "Invalid UTF-8 encoding"; // Invalid start byte
                }

                // Initialize codepoint with bits from start byte
                int codepoint = byteValue & ((1 << (7 - continuationBytes)) - 1);

                // Process continuation bytes
                for (int j = 0; j < continuationBytes; j++) {
                    i++;
                    if (i >= utf8Bytes.length) {
                        return "Invalid UTF-8 encoding"; // Incomplete sequence
                    }
                    byteValue = Integer.parseInt(utf8Bytes[i], 16);
                    if ((byteValue & 0xC0) != 0x80) {
                        return "Invalid UTF-8 encoding"; // Invalid continuation byte
                    }
                    codepoint = (codepoint << 6) | (byteValue & 0x3F);
                }

                // Append Unicode code point to the result string with "U+" prefix
                unicodeBuilder.append(String.format("U+%X ", codepoint));

                // Move to the next byte
                i++;
            }
        } catch (NumberFormatException e) {
            return "Invalid input format";
        }

        return unicodeBuilder.toString().trim();
    }


    public String convertFromUTF16(String utf16Input) {
        // Validate input format (XXXX XXXX)
        if (!utf16Input.matches("^[0-9A-Fa-f]{4} [0-9A-Fa-f]{4}$")) {
            return "Invalid input format. Please enter XXXX XXXX format.";
        }

        // Split the input into separate code units
        String[] utf16CodeUnits = utf16Input.trim().split("\\s+");
        StringBuilder unicodeBuilder = new StringBuilder();

        try {
            // Iterate through each UTF-16 code unit
            int i = 0;
            while (i < utf16CodeUnits.length) {
                // Parse the code unit as an integer
                int codeUnit = Integer.parseInt(utf16CodeUnits[i], 16);

                // Check if it's a surrogate pair
                if ((codeUnit & 0xFC00) == 0xD800 && i + 1 < utf16CodeUnits.length) {
                    // Check if the next code unit forms a valid surrogate pair
                    int nextCodeUnit = Integer.parseInt(utf16CodeUnits[i + 1], 16);
                    if ((nextCodeUnit & 0xFC00) == 0xDC00) {
                        // Calculate the code point from the surrogate pair
                        int codePoint = 0x10000 + ((codeUnit & 0x3FF) << 10) + (nextCodeUnit & 0x3FF);
                        // Append Unicode code point to the result string with "U+" prefix
                        unicodeBuilder.append(String.format("U+%X ", codePoint));
                        // Move to the next code unit
                        i++;
                    } else {
                        return "Invalid UTF-16 encoding"; // Invalid surrogate pair
                    }
                } else if ((codeUnit & 0xFC00) == 0xD800) {
                    return "Invalid UTF-16 encoding"; // Unmatched high surrogate
                } else if ((codeUnit & 0xFC00) == 0xDC00) {
                    return "Invalid UTF-16 encoding"; // Unmatched low surrogate
                } else {
                    // Single code unit represents a BMP character
                    // Append Unicode code point to the result string with "U+" prefix
                    unicodeBuilder.append(String.format("U+%X ", codeUnit));
                }

                // Move to the next code unit
                i++;
            }
        } catch (NumberFormatException e) {
            return "Invalid input format";
        }

        return unicodeBuilder.toString().trim();
    }


    public String convertFromUTF32(String utf32Input) {
        // Split the input into separate code units
        String[] utf32CodeUnits = utf32Input.trim().split("\\s+");
        StringBuilder unicodeBuilder = new StringBuilder();

        try {
            // Iterate through each UTF-32 code unit
            for (String codeUnitStr : utf32CodeUnits) {
                // Parse the code unit as an integer
                int codeUnit = Integer.parseInt(codeUnitStr, 16);

                // Append Unicode code point to the result string without leading zeroes
                unicodeBuilder.append(String.format("%X", codeUnit));
            }
        } catch (NumberFormatException e) {
            return "Invalid input format";
        }

        return "U+" + unicodeBuilder.toString().trim();
    }



}
