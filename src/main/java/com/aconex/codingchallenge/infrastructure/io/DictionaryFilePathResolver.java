package com.aconex.codingchallenge.infrastructure.io;

import com.aconex.codingchallenge.infrastructure.factory.InputStreamFactory;

import java.io.*;
import java.util.logging.Logger;

public class DictionaryFilePathResolver {

    private InputStreamFactory inputStreamFactory;

    private static final Logger LOGGER = Logger.getLogger(DictionaryFilePathResolver.class.getName());

    public InputStream getDictionaryFileInputStream() {
        String dictionaryFilePath = System.getProperty("DictionaryFilePath");
        LOGGER.info("System Property DictionaryFilePath set to "+dictionaryFilePath);
        if(dictionaryFilePath != null) {
            return inputStreamFactory.createInputStream(dictionaryFilePath);
        } else {
            LOGGER.info("Loading default dictionary file dictionary.txt from classpath");
            return getInputStreamToDefaultFile();
        }
    }

    private InputStream getInputStreamToDefaultFile() {
        return getClassLoader().getResourceAsStream("dictionary.txt");
    }

    protected ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    public void setInputStreamFactory(InputStreamFactory inputStreamFactory) {
        this.inputStreamFactory = inputStreamFactory;
    }
}
