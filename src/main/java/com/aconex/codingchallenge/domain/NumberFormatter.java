package com.aconex.codingchallenge.domain;

public class NumberFormatter {

    public Digit[] format(String number) {
        char[] chars = toCharArray(number);
        StringBuilder sb = new StringBuilder();
        removeInvalidCharacters(chars, sb);
        return toDigitsArray(sb.toString());
    }

    private char[] toCharArray(String word) {
        if(word == null) {
            return new char[0];
        }
        return word.toCharArray();
    }

    private void removeInvalidCharacters(char[] chars, StringBuilder sb) {
        for (char aChar : chars) {
            appendIfCharIsDigit(aChar, sb);
        }
    }

    private void appendIfCharIsDigit(char aChar, StringBuilder sb) {
        if(Character.isDigit(aChar)) {
            sb.append(aChar);
        }
    }

    private Digit[] toDigitsArray(String number) {
        char[] chars = number.toCharArray();
        Digit [] digits = new Digit[chars.length];
        for (int i = 0; i < chars.length; i++) {
            Digit digit = Digit.valueOf("DIGIT_" + chars[i]);
            digits[i] = digit;
        }
        return  digits;

    }


}
