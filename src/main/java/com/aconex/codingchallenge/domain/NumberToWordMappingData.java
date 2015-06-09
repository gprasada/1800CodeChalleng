package com.aconex.codingchallenge.domain;

public class NumberToWordMappingData {

    protected Digit[] number;
    protected String word;

    private StringBuilder sb = new StringBuilder();
    private boolean stopMatching;
    private int index = 0;

    public NumberToWordMappingData(Digit[] number, String word) {
        this.number = number;
        this.word = word;
        matchNumberToWord(number, word);
    }

    private void matchNumberToWord(Digit[] number, String word) {
        char[] wordChar = word.toCharArray();
        validateWordLength(number, wordChar);
        if(stopMatching) {
            return;
        }
        matchNumberToWord(number, wordChar);
    }

    private void matchNumberToWord(Digit[] number, char[] wordChar) {
        for (int i = 0; i < wordChar.length && !stopMatching; i++) {
            matchDigitToChar(number[i], wordChar[i]);
        }
    }

    private void matchDigitToChar(Digit digit, char c) {
        if( digit.isCharMappedToDigit(c) ){
            sb.append(c);
            index++;
        } else {
            stopMatching = true;
            sb = new StringBuilder();
        }
    }

    private void validateWordLength(Digit[] number, char[] wordChar) {
        if(wordChar.length > number.length) {
            stopMatching = true;
        }
    }

    public boolean done() {
        return  stopMatching ? stopMatching : !matchMoreWords();
    }

    private boolean matchMoreWords() {
        if(number.length > index) {
            return true;
        }
        return false;
    }

    public String getMappedNumber() {
        return sb.length() > 0 ? getFullNumber() : null;
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
