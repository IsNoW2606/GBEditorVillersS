package org.helmo.gbeditor.model;

public class Isbn {
    public static boolean isValid(String isbn) {
        isbn = isbn.replace("-", "");

        int temp = 0;
        for (int i = 0; i < 9; i++) {
            temp += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
        }

        int result;
        switch (temp %= 11) {
            case 0: result = 0; break;
            case 1 : result = 10; break;
            default: result = 11 - temp; break;
        }

        int lastChar = Character.toUpperCase(isbn.charAt(9)) == 'X' ? 10 : Character.getNumericValue(isbn.charAt(9));

        return lastChar == result;
    }
}
