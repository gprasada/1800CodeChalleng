package com.aconex.codingchallenge.domain;

public class NumberToWordMappingContext {

    protected Digit[] number;
    protected String word;

    private StringBuilder sb = new StringBuilder();
    private boolean stopMapping;
    private String msg;
    private int index = 0;

    public NumberToWordMappingContext(Digit[] number, String word) {
        this.number = number;
        this.word = word;
        mapNumberToWord(number, word);
    }

    private void mapNumberToWord(Digit[] number, String word) {
        char[] wordChar = word.toCharArray();
        validateWordLength(number, wordChar);
        if(stopMapping) {
            return;
        }
        mapNumberToWord(number, wordChar);
        stopMapping = true;
    }

    private void mapNumberToWord(Digit[] number, char[] wordChar) {
        for (int i = 0; i < wordChar.length && !stopMapping; i++) {
            mapDigitToChar(number[i], wordChar[i]);
        }
    }

    private void mapDigitToChar(Digit digit, char c) {
        if( digit.isCharMappedToDigit(c) ){
            sb.append(c);
            index++;
        } else {
            stopMapping = true;
            msg = "Number and Word do not match";
            sb = null;
        }
    }

    private void validateWordLength(Digit[] number, char[] wordChar) {
        if(wordChar.length > number.length) {
            stopMapping = true;
            msg = "Word is longer than Number";
        }
    }

    public boolean done() {
        return stopMapping;
    }

    public String getMsg() {
        return msg;
    }

    public String getMappedNumber() {
        return msg == null? getFullNumber() : null;
    }

    private String getFullNumber() {
        StringBuilder fullNumber = new StringBuilder();
        fullNumber.append( sb.toString() );
        if(index < number.length - 1) {
            fullNumber.append("-");
        }
        for (int i = index; i < number.length; i++) {
            fullNumber.append(number[i]);
        }

        return fullNumber.toString();
    }

}
