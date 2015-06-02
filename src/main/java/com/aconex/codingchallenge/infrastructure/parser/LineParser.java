package com.aconex.codingchallenge.infrastructure.parser;

public class LineParser {

    public String getNumber(String line) {
        char[] chars = line.toCharArray();
        StringBuilder number = new StringBuilder();
        for (char aChar : chars) {
            appendStringIfCharIsDigit(number ,aChar);
        }
        return number.toString();
    }

    private void appendStringIfCharIsDigit(StringBuilder number, char aChar) {
        if(Character.isDigit(aChar)) {
            number.append(aChar);
        }
    }


}
