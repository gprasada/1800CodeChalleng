package com.aconex.codingchallenge.domain;


import java.util.Arrays;

public enum Digit {

    DIGIT_0(new char[0], "0"),
    DIGIT_1(new char[0], "1"),
    DIGIT_2(new char[] {'A', 'B', 'C'}, "2"),
    DIGIT_3(new char[] {'D', 'E', 'F'}, "3"),
    DIGIT_4(new char[] {'G', 'H', 'I'}, "4"),
    DIGIT_5(new char[] {'J', 'K', 'L'}, "5"),
    DIGIT_6(new char[] {'M', 'N', 'O'}, "6"),
    DIGIT_7(new char[] {'P', 'Q', 'R', 'S'}, "7"),
    DIGIT_8(new char[] {'T', 'U', 'V'}, "8"),
    DIGIT_9(new char[] {'W', 'X', 'Y', 'Z'}, "9");

    private char[] mappedCharacters;
    private String value;

    Digit(char []mappedCharacters, String value) {
        this.mappedCharacters = mappedCharacters;
        this.value = value;
    }

    public boolean isCharMappedToDigit(char foo) {
        for (char mappedCharacter : mappedCharacters) {
            if(mappedCharacter == foo) {
                return true;
            }
        }
        return false;
    }

    public char[] getMappedCharacters() {
        return mappedCharacters;
    }

    @Override
    public String toString() {
        return value;
    }
}
