package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.domain.Dictionary;

public class DictionaryFileReaderCallBack implements FileReaderCallBackInterace {

    private Dictionary dictionary;

    @Override
    public void newLine(String newLine) {
        dictionary.addWord(newLine);
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }
}
