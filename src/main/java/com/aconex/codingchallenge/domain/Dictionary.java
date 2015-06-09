package com.aconex.codingchallenge.domain;

import java.util.*;

public class Dictionary {

    private TextRepository textRepository;

    private TextFormatter textFormatter;

    private NumberToWordMappingStrategy numberToWordMappingStrategy;

    public void addWord(String word) {
        String formattedWord = textFormatter.format(word);
        textRepository.addText(formattedWord);
    }

    public Set<String> matchNumbersToWord(Digit[] digits) {
        return numberToWordMappingStrategy.matchNumbersToWord(digits);
    }

    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void setTextFormatter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    public TextRepository getTextRepository() {
        return textRepository;
    }

    public TextFormatter getTextFormatter() {
        return textFormatter;
    }

    public void setNumberToWordMappingStrategy(NumberToWordMappingStrategy numberToWordMappingStrategy) {
        this.numberToWordMappingStrategy = numberToWordMappingStrategy;
    }

    public NumberToWordMappingStrategy getNumberToWordMappingStrategy() {
        return numberToWordMappingStrategy;
    }



}
