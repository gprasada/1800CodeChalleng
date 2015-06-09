package com.aconex.codingchallenge.infrastructure.util;

import com.aconex.codingchallenge.infrastructure.io.DictionaryFilePathResolver;
import com.aconex.codingchallenge.infrastructure.io.FileReader;
import com.aconex.codingchallenge.infrastructure.io.FileReaderCallBackInterace;

import java.io.InputStream;
import java.util.logging.Logger;

public class DictionaryLoader {

    private DictionaryFilePathResolver dictionaryFilePathResolver;

    private FileReader fileReader;

    private FileReaderCallBackInterace callBackInterace;

    public void setDictionaryFilePathResolver(DictionaryFilePathResolver dictionaryFilePathResolver) {
        this.dictionaryFilePathResolver = dictionaryFilePathResolver;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void setCallBackInterace(FileReaderCallBackInterace callBackInterace) {
        this.callBackInterace = callBackInterace;
    }

    public void loadDictionary() {
        InputStream dictionaryFileInputStream = dictionaryFilePathResolver.getDictionaryFileInputStream();
        fileReader.readFile(dictionaryFileInputStream, callBackInterace);
    }


}
