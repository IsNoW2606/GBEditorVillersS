package org.helmo.gbeditor.utils;

import java.util.List;

public class StringUtils {
    public static String capitalizeEveryWord(String string) {
        if (string.isEmpty()) {
            return "";
        }

        String[] result = string.split(" ");

        for (int i = 0; i < result.length; i++) {
            result[i] = capitalize(result[i]);
        }
        return String.join(" ", result) + (string.charAt(string.length() - 1) == ' ' ? " " : "");
    }

    public static String capitalize(String string) {
        return string.isEmpty() ? string : string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String firstCharUpperCase(String string) {
        return string.isEmpty() ? string : string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    public static String limiteLength(String string, int length) {
        if (string.length() < length) {
            return string;
        }
        return string.substring(0, length);
    }

    public static String removeBigSpace(String string) {
        if (string.isEmpty()) {
            return "";
        }
        return String.join(" ", string.split(" ")) + (string.charAt(string.length() - 1) == ' ' ? " " : "");
    }
}
