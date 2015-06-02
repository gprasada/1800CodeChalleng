package com.aconex.codingchallenge.infrastructure.io;

import java.io.*;

public class DictionaryFilePathResolver {

    public InputStream getDictionaryFileInputStream() {
        String dictionaryFilePath = System.getProperty("DictionaryFilePath");
        if(dictionaryFilePath != null) {
            return getInputStreamToExternalFile(dictionaryFilePath);
        } else {
            return getInputStreamToDefaultFile();
        }
    }

    private InputStream getInputStreamToExternalFile(String dictionaryFilePath) {
        File file = new File(dictionaryFilePath);
        try {
            return getInputStreamToExternalFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Dictionary File not found ", e);
        }
    }

    protected InputStream getInputStreamToExternalFile(File file) throws FileNotFoundException {
            return new FileInputStream(file);
    }

    private InputStream getInputStreamToDefaultFile() {
        return getClassLoader().getResourceAsStream("dictionary.txt");
    }

    protected ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }


}
