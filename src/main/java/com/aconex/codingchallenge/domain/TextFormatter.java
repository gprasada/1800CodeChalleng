package com.aconex.codingchallenge.domain;

import java.util.HashSet;
import java.util.Set;

public class TextFormatter {

    private Set<Character> allowedCharacters = new HashSet<Character>();

    public  TextFormatter() {
        addAllowedCharacters();
    }

    public String format(String word) {
        String wordInUpperCase = convertToUpperCase(word);
        String wordWithoutSpecialCharacters = filterSpecialCharacters(wordInUpperCase);
        return wordWithoutSpecialCharacters;
    }

    private String filterSpecialCharacters(String word) {
        char[] chars = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            appendToStringIfCharIsValid(aChar, sb);
        }
        return  sb.toString();
    }

    private void appendToStringIfCharIsValid(char aChar, StringBuilder sb) {
        if(allowedCharacters.contains(aChar)) {
            sb.append(aChar);
        }
    }

    private String convertToUpperCase(String word) {
        char[] chars = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(Character.toUpperCase(aChar));
        }
        return  sb.toString();
    }

    private void addAllowedCharacters() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] chars = allowedChars.toCharArray();
        for (char aChar : chars) {
            allowedCharacters.add(aChar);
        }
    }


}
