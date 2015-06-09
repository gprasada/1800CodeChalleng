package com.aconex.codingchallenge.infrastructure.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class InputStreamFactory {

    public InputStream createInputStream(String fileName) {
        File file = new File(fileName);
        try {
            return getInputStreamToExternalFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected InputStream getInputStreamToExternalFile(File file) throws FileNotFoundException {
            return new FileInputStream(file);
    }


}
