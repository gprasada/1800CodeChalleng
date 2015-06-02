package com.aconex.codingchallenge.domain;

import java.util.*;

public class Dictionary {

    private TextRepository textRepository;

    private TextFormatter textFormatter;

    public void addWord(String word) {
        String formattedWord = textFormatter.format(word);
        textRepository.addText(formattedWord);
    }

    public void setTextRepository(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public void setTextFormatter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }
}
